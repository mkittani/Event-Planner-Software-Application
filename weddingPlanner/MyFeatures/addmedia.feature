Feature: Add Media to Event

  Scenario: Successful addition of media by User
    Given the "User" is logged in and on the event page
    When the "User" uploads "mediaType" and "mediaFile"
    Then the "mediaType" is added to the event
    And the "User" can view the "mediaType"

  Scenario: Unsuccessful addition of media by User due to unsupported format
    Given the "User" is logged in and on the event page
    When the "User" attempts to upload "unsupportedMediaType" and "mediaFile"
    Then the "unsupportedMediaType" is not added to the event
    And the "User" receives an "Unsupported media type" error

  Scenario: Unsuccessful addition of media by User due to file size limit
    Given the "User" is logged in and on the event page
    When the "User" attempts to upload "mediaType" and "largeMediaFile"
    Then the "mediaType" is not added to the event
    And the "User" receives a "File size limit exceeded" error
