package wedding.Planner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventMediaManager {
    // Map to store event hall numbers and their associated media files
    private Map<String, List<Media>> eventMediaMap = new HashMap<>();

    // Method to add media to an event
    public void addMediaToEvent(String hallNumber, Media media) {
        // Check if the event already has media files associated with it
        List<Media> mediaList = eventMediaMap.getOrDefault(hallNumber, new ArrayList<>());

        // Add the new media to the list
        mediaList.add(media);

        // Put the updated list back into the map
        eventMediaMap.put(hallNumber, mediaList);
    }

    // Method to retrieve media for an event
    public List<Media> getMediaForEvent(String hallNumber) {
        // Return the list of media files for the event, or an empty list if none are associated
        return eventMediaMap.getOrDefault(hallNumber, new ArrayList<>());
    }

    // Method to remove media from an event
    public void removeMediaFromEvent(String hallNumber, Media media) {
        // Check if the event has media files associated with it
        List<Media> mediaList = eventMediaMap.get(hallNumber);

        // If there is such a list, remove the media from it
        if (mediaList != null) {
            mediaList.remove(media);

            // Update the map
            eventMediaMap.put(hallNumber, mediaList);
        }
    }

    // You could add more methods here for other functionalities such as updating media, listing all media, etc.
}

// Class to represent media files associated with events
class Media {
    private String type; // e.g., "image", "video"
    private String url; // URL or path to the media file

    public Media(String type, String url) {
        this.type = type;
        this.url = url;
    }

    // Getters and setters
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    // You could add more attributes and methods as needed, depending on the requirements for the media
}
