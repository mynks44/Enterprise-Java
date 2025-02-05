package ca.sheridancollege.suranim.controller;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import ca.sheridancollege.suranim.beans.Song;

@Controller
public class SongController {
	List<Song> songlist=new CopyOnWriteArrayList<Song>();
@GetMapping("/")
public String index(Model model) {
	model.addAttribute("song", new Song());
	return "index";
}
@PostMapping("/formPost")
public String formPost(Model model, @ModelAttribute Song song) {
	songlist.add(song);
	model.addAttribute("song", new Song());
	model.addAttribute("songlist", songlist);
	System.out.println(songlist);
	return "index";
	
}
}