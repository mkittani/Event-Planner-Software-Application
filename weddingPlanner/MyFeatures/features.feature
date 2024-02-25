Feature: Event Management

    Scenario: User Registration and Login
        Given a user accesses the system
        When the user clicks on "Register"
        And fills in the required information
        Then the user receives a confirmation email

    Scenario: Event Creation
        Given an event organizer is logged in
        When the organizer creates a new event
        And specifies date, time, location, theme, and description
        Then the event is created successfully

    Scenario: Venue Booking
        Given an event organizer is logged in
        When the organizer searches for available venues
        And reserves a venue for the event
        Then the venue is successfully booked

    Scenario: Vendor Management
        Given an event organizer is logged in
        When the organizer searches for catering vendors
        And negotiates a contract with a selected vendor
        Then the vendor is successfully booked

    Scenario: Budget Planning
        Given an event organizer is logged in
        When the organizer accesses the budget planning module
        And estimates and tracks expenses for the event
        Then the budget is successfully managed

    Scenario: Calendar and Scheduling
        Given an event organizer is logged in
        When the organizer views the calendar
        And schedules tasks, deadlines, and appointments
        Then the calendar is successfully updated

    Scenario: Ticketing and Registration
        Given an event organizer is logged in
        When the organizer generates e-tickets for participants
        And participants receive e-tickets via email
        Then the e-tickets are successfully generated and sent

    Scenario: Communication and Collaboration
        Given a messaging system is available
        When organizers, participants, and vendors communicate
        And receive reminders and notifications
        Then communication is successfully facilitated
