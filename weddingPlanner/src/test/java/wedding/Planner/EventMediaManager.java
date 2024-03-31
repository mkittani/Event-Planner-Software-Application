package wedding.Planner;
import java.util.*;
public class EventMediaManager {
    private final Map<String, List<Media>> eventMediaMap = new HashMap<>();

    public void addMediaToEvent(String hallNumber, Media media) {
        List<Media> mediaList = eventMediaMap.getOrDefault(hallNumber, new ArrayList<>());
        mediaList.add(media);

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


}


class Media {
    private final String type;
    private final String url;

    public Media(String type, String url) {
        this.type = type;
        this.url = url;
    }
    public String getType() {
        return type;
    }

    public String getUrl() {
        return url;
    }
}
