package com.lti.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.lti.entity.Album;
import com.lti.entity.Song;

public class AlbumSongDao extends GenericDao{
	
	public List<Song> fetchCopyrights(String copyright) {
		EntityManagerFactory emf = null;
		EntityManager em = null;
		try {
			emf = Persistence.createEntityManagerFactory("hibernate-demo");
			em = emf.createEntityManager();

			// JPQL-Java Persistence Query Language
			//select s.title from tbl_song s join 
			//tbl_album a on (a.id= s.album_id) 
			//where a.copyright='ThinkMusic';
			Query q = em.createQuery("select s from Song s join s.album a where a.copyright = :cr");
			q.setParameter("cr", copyright);
			List<Song> list = q.getResultList();
			return list;

		} finally {
			em.close();
			emf.close();
		}

	}
	
	public List<Album> fetchAlbumWithNumberOfSongs(int num) {
		EntityManagerFactory emf = null;
		EntityManager em = null;
		try {
			emf = Persistence.createEntityManagerFactory("hibernate-demo");
			em = emf.createEntityManager();

			// JPQL-Java Persistence Query Language
			//select s.title from tbl_song s join 
			//tbl_album a on (a.id= s.album_id) 
			//where a.copyright='ThinkMusic';
			Query q = em.createQuery("select a from Album a where a.songs.size >= :cr");
			q.setParameter("cr", num);
			List<Album> list = q.getResultList();
			return list;

		} finally {
			em.close();
			emf.close();
		}

	}


}
