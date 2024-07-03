package com.prunny.bookstore.service.ServiceImpl;

import com.prunny.bookstore.core.BlankCredentialsException;
import com.prunny.bookstore.core.InvalidCredentialsException;
import com.prunny.bookstore.dto.request.AuthorCreationRequest;
import com.prunny.bookstore.dto.request.AuthorUpdateRequest;
import com.prunny.bookstore.dto.request.IdRequest;
import com.prunny.bookstore.dto.response.ResponseDto;
import com.prunny.bookstore.enums.Nationality;
import com.prunny.bookstore.enums.ResponseCode;
import com.prunny.bookstore.model.Author;
import com.prunny.bookstore.model.Book;
import com.prunny.bookstore.repository.AuthorRepository;
import com.prunny.bookstore.repository.BookRepository;
import com.prunny.bookstore.repository.GenreRepository;
import com.prunny.bookstore.service.AuthorService;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final GenreRepository genreRepository;

    @Override
    public ResponseEntity<ResponseDto<Boolean>> createAuthor(AuthorCreationRequest authorCreationRequest) {
        ResponseDto<Boolean> resp = new ResponseDto<>();

        if(StringUtils.isBlank(authorCreationRequest.getFirstName())){
            throw new BlankCredentialsException("First name cannot be blank");
        }
        if(StringUtils.isBlank(authorCreationRequest.getLastName())){
            throw new BlankCredentialsException("Last name cannot be blank");
        }
        if(StringUtils.isBlank(authorCreationRequest.getOtherName())){
            throw new BlankCredentialsException("Other name cannot be blank");
        }
        if(StringUtils.isBlank(authorCreationRequest.getEmail())){
            throw new BlankCredentialsException("Email cannot be blank");
        }
        if(StringUtils.isBlank(authorCreationRequest.getPhoneNumber())){
            throw new BlankCredentialsException("Phone number name cannot be blank");
        }
        if(StringUtils.isBlank(authorCreationRequest.getBiography())){
            throw new BlankCredentialsException("Biography cannot be blank");
        }
        if(StringUtils.isBlank(authorCreationRequest.getWebsite())){
            throw new BlankCredentialsException("Website cannot be blank");
        }
        if(Objects.isNull(authorCreationRequest.getNationality())){
            throw new BlankCredentialsException("Nationality cannot be blank");
        }
        Nationality nationality;
        try{
            nationality = Nationality.valueOf(authorCreationRequest.getNationality());
        }catch (Exception e){
            throw new InvalidCredentialsException("Invalid nationality");
        }
        authorRepository.save(Author.builder()
                        .firstName(authorCreationRequest.getFirstName())
                        .lastName(authorCreationRequest.getLastName())
                        .otherName(authorCreationRequest.getOtherName())
                        .email(authorCreationRequest.getEmail())
                        .biography(authorCreationRequest.getBiography())
                        .website(authorCreationRequest.getWebsite())
                        .phoneNumber(authorCreationRequest.getPhoneNumber())
                        .nationality(nationality)
                        .createdAt(Timestamp.valueOf(LocalDateTime.now()))
                .build());

        resp.setData(true);
        resp.setStatusCode(ResponseCode.SUCCESSFUL.getCode());
        resp.setStatusMessage(ResponseCode.SUCCESSFUL.getMessage());
        return new ResponseEntity<>(resp, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ResponseDto<Boolean>> updateAuthor(AuthorUpdateRequest authorUpdateRequest) {
        ResponseDto<Boolean> resp = new ResponseDto<>();

        if(Objects.isNull(authorUpdateRequest.getAuthorId())){
            throw new BlankCredentialsException("Author id cannot be blank");
        }
        if(StringUtils.isBlank(authorUpdateRequest.getFirstName())){
            throw new BlankCredentialsException("First name cannot be blank");
        }
        if(StringUtils.isBlank(authorUpdateRequest.getLastName())){
            throw new BlankCredentialsException("Last name cannot be blank");
        }
        if(StringUtils.isBlank(authorUpdateRequest.getOtherName())){
            throw new BlankCredentialsException("Other name cannot be blank");
        }
        if(StringUtils.isBlank(authorUpdateRequest.getEmail())){
            throw new BlankCredentialsException("Email cannot be blank");
        }
        if(StringUtils.isBlank(authorUpdateRequest.getPhoneNumber())){
            throw new BlankCredentialsException("Phone number name cannot be blank");
        }
        if(StringUtils.isBlank(authorUpdateRequest.getBiography())){
            throw new BlankCredentialsException("Biography cannot be blank");
        }
        if(StringUtils.isBlank(authorUpdateRequest.getWebsite())){
            throw new BlankCredentialsException("Website cannot be blank");
        }
        if(Objects.isNull(authorUpdateRequest.getNationality())){
            throw new BlankCredentialsException("Nationality cannot be blank");
        }
        Nationality nationality;
        try{
            nationality = Nationality.valueOf(authorUpdateRequest.getNationality());
        }catch (Exception e){
            throw new InvalidCredentialsException("Invalid nationality");
        }
        Author author = authorRepository.findById(authorUpdateRequest.getAuthorId())
                .orElseThrow(() -> new InvalidCredentialsException("Invalid author id"));

        author.setFirstName(authorUpdateRequest.getFirstName());
        author.setLastName(authorUpdateRequest.getLastName());
        author.setOtherName(authorUpdateRequest.getOtherName());
        author.setEmail(authorUpdateRequest.getEmail());
        author.setBiography(authorUpdateRequest.getBiography());
        author.setWebsite(authorUpdateRequest.getWebsite());
        author.setPhoneNumber(authorUpdateRequest.getPhoneNumber());
        author.setNationality(nationality);

        authorRepository.save(author);

        resp.setData(true);
        resp.setStatusCode(ResponseCode.SUCCESSFUL.getCode());
        resp.setStatusMessage(ResponseCode.SUCCESSFUL.getMessage());
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseDto<Author>> getById(Long id) {
        ResponseDto<Author> resp = new ResponseDto<>();
        if(Objects.isNull(id)){
            throw new BlankCredentialsException("Author id cannot be blank");
        }
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new InvalidCredentialsException("Invalid author id"));

        resp.setData(author);
        resp.setStatusCode(ResponseCode.SUCCESSFUL.getCode());
        resp.setStatusMessage(ResponseCode.SUCCESSFUL.getMessage());
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseDto<Page<Author>>> getAllAuthors(int page, int pageRecord) {
        ResponseDto<Page<Author>> resp = new ResponseDto<>();
        Pageable pageable = PageRequest.of(page, pageRecord);
        Page<Author> author = authorRepository.findAllByOrderByIdDesc(pageable);
        resp.setData(author);
        resp.setStatusCode(ResponseCode.SUCCESSFUL.getCode());
        resp.setStatusMessage(ResponseCode.SUCCESSFUL.getMessage());
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseDto<Boolean>> delete(IdRequest idRequest) {
        ResponseDto<Boolean> resp = new ResponseDto<>();
        if (Objects.isNull(idRequest.getId())) {
            throw new BlankCredentialsException("Author id cannot be blank");
        }
        if (idRequest.getId() <= 0) {
            throw new InvalidCredentialsException("Invalid author id");
        }
        Author author = authorRepository.findById(idRequest.getId())
                .orElseThrow(() -> new InvalidCredentialsException("Invalid author id"));

        // Delete related books first
        List<Book> books = bookRepository.findByAuthorId(idRequest.getId());
        if (!books.isEmpty()) {
            bookRepository.deleteAll(books);
        }
        List<Book> book = bookRepository.findByGenreId(idRequest.getId());
        if (!book.isEmpty()) {
            bookRepository.deleteAll(book);
        }

        authorRepository.delete(author);
        resp.setStatusCode(ResponseCode.SUCCESSFUL.getCode());
        resp.setStatusMessage(ResponseCode.SUCCESSFUL.getMessage());
        resp.setData(true);
        return new ResponseEntity<>(resp, HttpStatus.NO_CONTENT);
    }
}
