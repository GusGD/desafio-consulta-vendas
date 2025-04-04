package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SaleSummaryDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;

	public SaleMinDTO findById(Long id) {
		Sale sale = repository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sale not found"));
		return new SaleMinDTO(sale);
	}

	public Page<SaleMinDTO> getReport(String minDateStr, String maxDateStr, String sellerName, Pageable pageable) {
		LocalDate maxDate = (maxDateStr == null) ? LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault())
				: LocalDate.parse(maxDateStr);

		LocalDate minDate = (minDateStr == null) ? maxDate.minusYears(1) : LocalDate.parse(minDateStr);

		sellerName = (sellerName == null) ? "" : sellerName.trim().toLowerCase();

		return repository.findSalesReport(minDate, maxDate, sellerName, pageable);
	}

	public List<SaleSummaryDTO> getSummary(String minDateStr, String maxDateStr) {
		LocalDate maxDate = (maxDateStr == null) ? LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault())
				: LocalDate.parse(maxDateStr);

		LocalDate minDate = (minDateStr == null) ? maxDate.minusYears(1) : LocalDate.parse(minDateStr);

		return repository.findSalesSummary(minDate, maxDate);
	}
}
