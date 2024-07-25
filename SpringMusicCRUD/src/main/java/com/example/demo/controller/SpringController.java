package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Music;
import com.example.demo.form.MusicForm;
import com.example.demo.service.MusicService;


@Controller
public class SpringController {
	@Autowired
	MusicService service;
	
	@GetMapping("index")
	public String indexView() {
		return "index";
	}
	
	@PostMapping(value="menu", params="select")
	public String listView(Model model) {
		Iterable<Music> list = service.findAll();
		model.addAttribute("list", list);
		return "list";
	}
	
	//登録
	@PostMapping(value="menu", params="input")
	public String InputView() {
		return "input";
	}
	@PostMapping(value = "menu", params = "confirm")
    public String ConfirmView(MusicForm f) {
        return "confirm";
    }

	@PostMapping(value = "menu", params = "complete")
	public String CompleteView(MusicForm f) {
	    Music music = new Music(f.getSong_id(),f.getSong_name(),f.getSinger());
	    service.insertMusic(music);
	    return "complete";
	}
	
	//削除
	@PostMapping(value="menu", params="delete")
	public String DeleteView(Model model) {
		Iterable<Music> list = service.findAll();
		model.addAttribute("list", list);
		return "delete";
	}
	@PostMapping(value = "menu", params = "delete-confirm")
    public String deleteConfirmView(@RequestParam("song_id") Integer song_id, Model model) {
        Music music = service.findById(song_id).orElse(null);
        model.addAttribute("music", music);
        return "delete_confirm";
    }

    @PostMapping(value = "menu", params = "delete-complete")
    public String deleteCompleteView(@RequestParam("song_id") Integer song_id) {
        service.deleteMusic(song_id);
        return "delete_complete";
    }
	
	//更新
    @PostMapping(value = "menu", params = "update")
    public String updateView(Model model) {
        Iterable<Music> list = service.findAll();
        model.addAttribute("list", list);
        return "update";
    }

    @PostMapping(value = "menu", params = "update-input")
    public String updateInputView(@RequestParam("song_id") Integer song_id, Model model) {
        Music music = service.findById(song_id).orElse(null);
        model.addAttribute("music", music);
        return "update_input";
    }

    @PostMapping(value = "menu", params = "update-complete")
    public String updateCompleteView(@RequestParam("song_id") Integer song_id,
                                     @RequestParam("song_name") String song_name,
                                     @RequestParam("singer") String singer) {
        Music music = new Music(song_id, song_name, singer);
        service.updateMusic(music);
        return "update_complete";
    }
}
