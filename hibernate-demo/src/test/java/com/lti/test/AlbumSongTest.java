package com.lti.test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.lti.dao.AlbumSongDao;
import com.lti.dao.GenericDao;
import com.lti.entity.Album;
import com.lti.entity.Passport;
import com.lti.entity.Song;

public class AlbumSongTest {
	
	@Test
	public void addAlbum() {
		Album album = new Album();
		album.setName("Jersey");
		album.setCopyright("SonyMusic");
		album.setReleaseDate(LocalDate.of(2020, 03, 13));
		GenericDao dao = new GenericDao();
		dao.save(album);
	}
	
	@Test
	public void addSongToAnExistingAlbum() {
		Song song = new Song();
		song.setTitle("Vaathi coming");
		song.setArtist("Anirudh");
		song.setDuration(3.5);
		
		GenericDao dao = new GenericDao();
		Album album = (Album) dao.fetch(Album.class, 89);
		song.setAlbum(album);
		dao.save(album);
		dao.save(song);
		
	}
	
	@Test
	public void addAlbumAlongWithSongs() {
		
		GenericDao dao = new GenericDao();
		Album album = new Album();
		album.setName("Hits of shreya");
		album.setCopyright("ThinkMusic");
		album.setReleaseDate(LocalDate.of(2018, 03, 23));
		
		Song song = new Song();
		song.setTitle("Munbe vaa");
		song.setArtist("A.R.Rahman");
		song.setDuration(5.0);
		song.setAlbum(album);
		
		Song song1 = new Song();
		song1.setTitle("Mailaanji");
		song1.setArtist("Imaan");
		song1.setDuration(5.8);
		song1.setAlbum(album);
		
		ArrayList<Song> songs = new ArrayList<Song>();
		songs.add(song);
		songs.add(song1);
		
		album.setSongs(songs);	
		dao.save(album);
		
	}
	
	@Test
	public void getSongsByCopyright() {
		AlbumSongDao dao = new AlbumSongDao();
		List<Song> p = dao.fetchCopyrights("ThinkMusic");
		for (Song e : p ) {
			System.out.println(e.getArtist() + ", " + e.getDuration() + ", " +e.getTitle());
		}
		
	}
	
	@Test
	public void getAlbumBySongs() {
		AlbumSongDao dao = new AlbumSongDao();
		List<Album> p = dao.fetchAlbumWithNumberOfSongs(2);
		for (Album e : p ) {
			System.out.println(e.getCopyright() + ", " + e.getName() + ", " +e.getReleaseDate());
		}
		
	}
	
	@Test
	public void fetchAlbum() {
		GenericDao dao = new GenericDao();
		Album album = (Album) dao.fetch(Album.class,89);
		System.out.println("Album name: "+album.getName());
		System.out.println("Album date: "+album.getReleaseDate());
		
		for(Song s:album.getSongs()) {
			System.out.println("Title: "+s.getTitle());
			System.out.println("Artist: "+s.getArtist());
		}
	}
	
	@Test
	public void fetchSong() {
		GenericDao dao = new GenericDao();
		Song song = (Song) dao.fetch(Song.class, 90);
		System.out.println("Song title: "+song.getTitle());
		System.out.println("Song sung by: "+song.getArtist());
		System.out.println("Duration: "+song.getDuration());
		System.out.println("Album name: "+song.getAlbum().getName());
		System.out.println("Album Copyrights: "+song.getAlbum().getCopyright());
	}

}
