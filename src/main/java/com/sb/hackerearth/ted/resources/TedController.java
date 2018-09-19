/**
 * 
 */
package com.sb.hackerearth.ted.resources;

import java.util.Set;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sb.hackerearth.ted.pojo.ExcelData;
import com.sb.hackerearth.ted.pojo.Page;
import com.sb.hackerearth.ted.pojo.Response;
import com.sb.hackerearth.ted.service.TedService;

/**
 * @author ankur.mahajan
 * @written 18-Sep-2018
 */
@RestController
public class TedController {

	private static final Logger LOGGER = Logger.getLogger(TedController.class.getName());

	@Autowired
	private TedService tedService;

	@PostConstruct
	public void init() {
		try {
			tedService.readCsvFile();
		}
		catch (Exception e) {
			LOGGER.error("Unable to process CSV while startup.");
		}
	}

	@RequestMapping(value = "/status", method = RequestMethod.GET)
	private ResponseEntity<Response> getStatus() {
		Response response = new Response();
		response.setSuccess(true);
		response.setMessage("Service is active!!");
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/tags", method = RequestMethod.GET)
	private ResponseEntity<Response> getUniqueTags() {
		Response response = new Response();
		Set<String> tags = tedService.getUniqueTags();
		response.setSuccess(true);
		response.setMessage("Unique tags.");
		response.setData(tags);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/ted/{tag}", method = RequestMethod.GET)
	private ResponseEntity<Response> getTedsByTag(@PathVariable String tag) {
		Response response = new Response();
		Set<ExcelData> teds = tedService.getTedsByTag(tag);
		response.setSuccess(true);
		response.setMessage("Teds by a tag name.");
		response.setData(teds);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/ted/{tag}/page", method = RequestMethod.GET)
	private ResponseEntity<Response> getTedsByTagByPagination(@PathVariable String tag, @RequestParam Long page,
			@RequestParam Long size, @RequestParam boolean sortByDate, @RequestParam boolean sortByViews) {
		Response response = new Response();
		Page pageObject = tedService.getTedsByTagByPagination(tag, page, size, sortByDate, sortByViews);
		response.setSuccess(true);
		response.setMessage("Teds by a tag name with pagination and sorting.");
		response.setData(pageObject);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/ted/search", method = RequestMethod.GET)
	private ResponseEntity<Response> tedSearch(@RequestParam(required = false) String name,
			@RequestParam(required = false) String speakerOccupation, @RequestParam(required = false) String event,
			@RequestParam(required = false) String title, @RequestParam Long page, @RequestParam Long size,
			@RequestParam boolean sortByDate, @RequestParam boolean sortByViews) {
		Response response = new Response();
		Page pageObject = tedService.tedSearch(name == null || name.equals("") ? null : name,
				speakerOccupation == null || speakerOccupation.equals("") ? null : speakerOccupation,
				event == null || event.equals("") ? null : event, title == null || title.equals("") ? null : title, page, size,
				sortByDate, sortByViews);
		response.setSuccess(true);
		response.setMessage("Search Teds by parameters with pagination and sorting.");
		response.setData(pageObject);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}

}
