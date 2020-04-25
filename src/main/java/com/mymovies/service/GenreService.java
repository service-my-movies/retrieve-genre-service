package com.mymovies.service;

import com.mymovies.dto.GenreDTO;
import com.mymovies.dto.MovieDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

@Service
public class GenreService implements IGenreService {

	@Value("${resource.api.url}")
	private String BASE_URL;
	
	@Value("${resource.api.url.image}")
	private String BASE_URL_IMAGE;
	
	@Value("${resource.api.key}")
	private String API_KEY;
	
	@Value("${resource.api.language}")
	private String Language;

	private static final Logger LOGGER = LoggerFactory.getLogger(GenreService.class);
	
	private RestTemplate restTemplate = new RestTemplate();
	
	public ArrayList<GenreDTO> getAPI_Genre(String movie_id) {

		MovieDTO movie = null;
		
		try {
			movie = restTemplate.getForObject(BASE_URL+movie_id+API_KEY+Language, MovieDTO.class);
		} catch (Exception e) {
			LOGGER.error("Unexpected Error: getAPI_Genre: " + e);
		}

		return movie.getGenres();

	}

}
