# Requirements

## The task

The task is to develop an admin panel for the 'business day' of our school.

## Model - Conceptional

The basis of the app is shown in the following model:

![UML Diagram](https://www.plantuml.com/plantuml/png/ZPFFZjGm3CRlVOhSqHw0snxGCloHLW8qOheFu2PcQaZiAF5GmSAxanBjHEd-WTw-dpzzTXhdAYiaFGK_3DP3pkQr2yJcTlXz1suofg9D-PvvuQCXLyL14nN94B03-178ToGkRfHlo2rqjJmO-a4i-BUWE590iyp81sJjql9f-egsPrPOjpvG1Jk7Wfy_mbyymb-sGVUGb2n5K19-S68dfycZYOaizWJ8tR2I_oJBPjYmTiaLJY8UWStr-uqUUxtRwElFLHEhiQmZb9wRI4PqUIyBffxBmZiHdSy1rNc8OB4T_Os42Fp3t7R39ynHE5Ffrl4u2_V4sb7-uzmHaZAcF5DilTzzByOd5wGkHvVlOKBVNS6qdd9_xsOcEkxEbTRqBtjorLFxOvb-Od-GajhAAh49Zr-qgOMSpatEICTProdgqxku-FMgFhfBav5T7bPHS7qqboMwajNOxsSfnhF6FDpTuB-TNR7uwmKKb_2zMlrFfGOw35V8xXJy7m00)

A teacher organizes a Business Day as an admin. First, they create an event on a specific date (when) with a name (label, e.g., Business Day 2025) and set prices for regular booths (boothPrice) and larger booths (bigBoothPrice, which are twice the size). Then, they send an invitation via email (whenSent, subject) to companies. The email includes content and may have optional links or attachments.

A contact person (responsible) registers a company for the event through a form in the link or attachment. They also specify the company’s interests and the equipment needed for their booth (IF/../IR, needsSpace, etc. – see registration form below).

For the next Business Day/event, the admin can select some or all companies before sending the invitation email. They can assign these companies to their previous or an updated booth with the required equipment. The admin can add or update companies at any time. This includes setting an optional ccTo email address. If provided, the specified email address (EDK, ORG, KAA, etc.) will receive a copy of all emails sent by the admin. Ideally, this also includes all replies from companies via "reply all" so that the contact person stays informed.

The admin sets the prices for regular and large booths (boothPrice and bigBoothPrice) for each Business Day. When creating participation entries, registered companies receive their respective price (price), depending on whether they have a regular or large booth (big). Business partners of the HTL (partner) can participate in Business Days for free.

![UI-Template](images/UI-Template.png)
