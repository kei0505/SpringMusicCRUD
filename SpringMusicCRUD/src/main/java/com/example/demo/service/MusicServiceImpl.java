package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Music;
import com.example.demo.repository.MusicCrudRepository;

@Service
public class MusicServiceImpl implements MusicService{
	@Autowired
	MusicCrudRepository repository;
	
	@Override
	public Iterable<Music> findAll() {
		return repository.findAll();
	}
	@Override
	public Optional<Music> findById(Integer song_id) {
		return repository.findById(song_id);
	}
	
	@Override
	public void insertMusic(Music music) {
		repository.save(music);
	}
	
	@Override
    @Transactional
    public void deleteMusic(Integer song_id) {
        repository.deleteById(song_id);
    }

    @Override
    public void updateMusic(Music music) {
        repository.save(music);
    }
}
