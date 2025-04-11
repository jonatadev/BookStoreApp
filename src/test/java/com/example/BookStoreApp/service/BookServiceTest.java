package com.example.BookStoreApp.service;

import com.example.BookStoreApp.model.Book;
import com.example.BookStoreApp.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @Test
    void ShouldCreateBook() {
        var book = new Book();
        book.setTitle("Test Book");

        when(bookRepository.save(book)).thenReturn(book);

        var createdBook = bookService.createBook(book);

        assertNotNull(createdBook);
        assertEquals("Test Book", createdBook.getTitle());
        verify(bookRepository, times(1)).save(book);
    }

    @Test
    void ShouldGetAllBooks() {
        var book1 = new Book();
        var book2 = new Book();
        when(bookRepository.findAll()).thenReturn(Arrays.asList(book1, book2));

        List<Book> books = bookService.getAllBooks();

        assertEquals(2, books.size());
        verify(bookRepository, times(1)).findAll();
    }

    @Test
    void ShouldGetBookById() {
        var book = new Book();
        book.setId(1L);
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        var foundBook = bookService.getBookById(1L);

        assertTrue(foundBook.isPresent());
        assertEquals(1L, foundBook.get().getId());
        verify(bookRepository, times(1)).findById(1L);
    }

    @Test
    void ShouldUpdateBook() {
        var existingBook = new Book();
        existingBook.setId(1L);
        existingBook.setTitle("Old Title");

        var updatedDetails = new Book();
        updatedDetails.setTitle("New Title");

        when(bookRepository.findById(1L)).thenReturn(Optional.of(existingBook));
        when(bookRepository.save(existingBook)).thenReturn(existingBook);

        Book updatedBook = bookService.updateBook(1L, updatedDetails);

        assertEquals("New Title", updatedBook.getTitle());
        verify(bookRepository, times(1)).findById(1L);
        verify(bookRepository, times(1)).save(existingBook);
    }

    @Test
    void ShouldDeleteBook() {
        var bookId = 1L;

        doNothing().when(bookRepository).deleteById(bookId);

        bookService.deleteBook(bookId);

        verify(bookRepository, times(1)).deleteById(bookId);
    }
}