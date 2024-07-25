package com.example.demo.service;

import java.util.Optional;

import com.example.demo.entity.Music;

public interface MusicService {
	Iterable<Music> findAll();
	Optional<Music> findById(Integer song_id); 
	void insertMusic(Music music);
	void deleteMusic(Integer song_id);
	void updateMusic(Music music);
}
