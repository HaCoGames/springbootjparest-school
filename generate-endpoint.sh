#!/bin/sh

# This script is used to generate an endpoint
# including the repositories, the services,
# their interfaces and their POJO classes.

# Check if at least one parameter is provided
if [ -z "$1" ]; then
    echo "Usage: $0 <ClassName> [TargetDirectory]"
    exit 1
fi

cd src/main/kotlin/dev/hafnerp/springbootjparest05_hafner/

className=$1
lowerClassName=$(echo "$className" | awk '{print tolower(substr($0,1,1)) substr($0,2)}')

# Set target directory (default to current directory if not provided)
targetDir=${2:-$(pwd)}

# Ensure the target directory exists
mkdir -p "$targetDir"

# Create folders
mkdir -p "$targetDir/controllers" "$targetDir/repositories" "$targetDir/services"

# Generate Controller
cat <<EOF > "$targetDir/controllers/${className}Controller.java"
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/${lowerClassName}s")
public class ${className}Controller {

    @Autowired
    private ${className}ServiceImpl ${lowerClassName}Service;

    @GetMapping("/")
    public List<${className}> getAll() {
        return ${lowerClassName}Service.getAll${className}s();
    }

    @GetMapping("/{id}")
    public ${className} getById(@PathVariable Long id) {
        return ${lowerClassName}Service.get${className}(id);
    }

    @PostMapping("/")
    public ${className} create(@RequestBody ${className} entity) {
        return ${lowerClassName}Service.create${className}(entity);
    }
}
EOF

# Generate Repository
cat <<EOF > "$targetDir/repositories/${className}Repository.java"
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ${className}Repository extends JpaRepository<${className}, Long> {
}
EOF

# Generate Service Interface
cat <<EOF > "$targetDir/services/${className}Service.java"
import java.util.List;

public interface ${className}Service {
    List<${className}> getAll${className}s();
    ${className} get${className}(Long id);
    ${className} create${className}(${className} entity);
}
EOF

# Generate Service Implementation
cat <<EOF > "$targetDir/services/${className}ServiceImpl.java"
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ${className}ServiceImpl implements ${className}Service {

    @Autowired
    private ${className}Repository repo;

    @Override
    public List<${className}> getAll${className}s() {
        return repo.findAll();
    }

    @Override
    public ${className} get${className}(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public ${className} create${className}(${className} entity) {
        return repo.save(entity);
    }
}
EOF

echo "Files generated successfully in $targetDir/controllers, $targetDir/repositories, and $targetDir/services/"