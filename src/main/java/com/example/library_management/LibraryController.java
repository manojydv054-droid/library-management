package com.example.library_management;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
public class LibraryController {

    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;
    private final IssuedBookRepository issuedBookRepository;

    public LibraryController(
            BookRepository bookRepository,
            MemberRepository memberRepository,
            IssuedBookRepository issuedBookRepository) {

        this.bookRepository = bookRepository;
        this.memberRepository = memberRepository;
        this.issuedBookRepository = issuedBookRepository;
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/books")
    public String books(Model model) {
        model.addAttribute("books", bookRepository.findAll());
        return "books";
    }

    @GetMapping("/members")
    public String members(Model model) {
        model.addAttribute("members", memberRepository.findAll());
        return "members";
    }

    @GetMapping("/issues")
    public String issues(Model model) {
        model.addAttribute("issues", issuedBookRepository.findAll());
        model.addAttribute("books", bookRepository.findAll());
        model.addAttribute("members", memberRepository.findAll());

        return "issued-books";
    }

    @PostMapping("/issue")
    public String issueBook(
            @RequestParam int bookId,
            @RequestParam int memberId) {

        IssuedBook issuedBook = new IssuedBook();

        issuedBook.setBookId(bookId);
        issuedBook.setMemberId(memberId);
        issuedBook.setIssueDate(LocalDate.now());
        issuedBook.setReturnDate(null);

        issuedBookRepository.save(issuedBook);

        return "redirect:/issues";
    }

    @PostMapping("/return")
    public String returnBook(@RequestParam int issueId) {

        IssuedBook issuedBook =
                issuedBookRepository.findById(issueId).orElseThrow();

        issuedBook.setReturnDate(LocalDate.now());

        issuedBookRepository.save(issuedBook);

        return "redirect:/issues";
    }
}