package nl.han.dea.persistence;

import nl.han.dea.DTO.Playlist;

import java.util.ArrayList;

public class PlaylistDao {


    public ArrayList<Playlist> playlists(String token) {

        ArrayList<Playlist> playlists = new ArrayList<>();
        if ("12345".equals(token)) {
            playlists.add(new Playlist());
            playlists.get(0).setId(1);
            playlists.get(0).setName("Heavy Metal");
            playlists.get(0).setOwner(true);

            playlists.add(new Playlist());
            playlists.get(1).setId(2);
            playlists.get(1).setName("Pop");
            playlists.get(1).setOwner(false);
        }
        return playlists;
    }
}