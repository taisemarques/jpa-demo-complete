package com.example.jpademocomplete.services;

import com.example.jpademocomplete.bo.PriceOfferBO;
import com.example.jpademocomplete.entities.Book;
import com.example.jpademocomplete.entities.PriceOffer;
import com.example.jpademocomplete.repositories.PriceOfferRepository;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PriceOfferService {

    private PriceOfferRepository priceOfferRepository;

    public PriceOfferService(PriceOfferRepository priceOfferRepository){
        this.priceOfferRepository = priceOfferRepository;
    }

    private Converter<PriceOfferBO, PriceOffer> priceOfferBOToPriceOffer = in -> {
        PriceOffer priceOffer = new PriceOffer();
        priceOffer.setNewPrice(in.getNewPrice());
        priceOffer.setPromotionalText(in.getPromotionalText());
        priceOffer.setActive(true);
        return priceOffer;
    };

    private Converter<PriceOffer, PriceOfferBO> priceOfferToPriceOfferBO = in -> {
        PriceOfferBO priceOffer = PriceOfferBO.builder()
                .newPrice(in.getNewPrice())
                .promotionalText(in.getPromotionalText())
                .build();
        return priceOffer;
    };

    @Transactional
    public PriceOffer createPriceOffer(PriceOfferBO priceOfferBO, Book book) {
        priceOfferRepository.inactivatePriceOfferByBookId(book.getBookId());
        PriceOffer priceOffer = priceOfferBOToPriceOffer.convert(priceOfferBO);
        priceOffer.setCreation(new Date());
        priceOffer.setBook(book);
        return priceOfferRepository.save(priceOffer);
    }

    public List<PriceOfferBO> listPriceOfferToListPriceOfferBO(List<PriceOffer> priceOffers) {
        return priceOffers.stream()
                .map(priceOffer -> priceOfferToPriceOfferBO.convert(priceOffer))
                .collect(Collectors.toList());
    }
}
