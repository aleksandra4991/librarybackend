package com.kodilla.librarybackend.controller;

import com.kodilla.librarybackend.domain.Volume;
import com.kodilla.librarybackend.domain.VolumeDto;
import com.kodilla.librarybackend.exceptions.VolumeNotFoundException;
import com.kodilla.librarybackend.mapper.VolumeMapper;
import com.kodilla.librarybackend.service.VolumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/myLibrary")
public class VolumeController {

    @Autowired
    private VolumeService volumeService;

    @Autowired
    private VolumeMapper volumeMapper;

    @RequestMapping(method = RequestMethod.GET, value = "/books/title")
    public List <VolumeDto> getSpecifiedGoogleBook(@RequestHeader("title")String title){
        return volumeMapper.mapToBookDtoList(volumeService.fetchSpecifiedGoogleBook(title));
    }

    /*@RequestMapping(method = RequestMethod.PUT, value = " /book/putInCart")
    public CartBookAdderDto putFoundBookInTheCart(@RequestBody VolumeDto volumeDto, @PathVariable String book, @PathVariable Long cartId){
        return volumeService.putFoundVolumeInCart(volumeMapper.mapToBook(volumeDto),book,cartId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/book/alreadyInCart")
    public VolumeDto getVolumePutIntoCart(@RequestParam CartBookAdderDto cartBookAdderDto){
        return volumeService.getBookPutInCart(cartBookAdderDto);
    }*/

    @RequestMapping(method = RequestMethod.GET, value = "/book")
    public VolumeDto getBook(@RequestParam Long bookId) throws VolumeNotFoundException {
        return volumeMapper.mapToBookDto((Volume) volumeService.getBook(bookId));
    }


    @RequestMapping(method = RequestMethod.POST, value = "/book", consumes = APPLICATION_JSON_VALUE)
    public VolumeDto createBook(@RequestBody VolumeDto volumeDto){
        return volumeMapper.mapToBookDto(volumeService.createBook(volumeMapper.mapToBook(volumeDto)));
    }

    /*@RequestMapping(method = RequestMethod.PUT,value = "updateBook",consumes = APPLICATION_JSON_VALUE)
    public BookDto updateBook(@RequestBody BookDto bookDto) {
        return volumeMapper.mapToBookDto(volumeService.createBook(volumeMapper.mapToBook(bookDto)));
    }*/

    @RequestMapping(method = RequestMethod.DELETE,value = "/book")
    public void deleteBook(@RequestParam Long bookId) throws VolumeNotFoundException {
        volumeService.deleteBook(bookId);
    }

}


