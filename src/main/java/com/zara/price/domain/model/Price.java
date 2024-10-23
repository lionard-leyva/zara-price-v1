package com.zara.price.domain.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Table("prices")
public class Price {
    @Id
    private Long id;
    @Column("brand_id")
    private Long brandId;
    @Column("start_date")
    private LocalDateTime startDate;
    @Column("end_date")
    private LocalDateTime endDate;
    @Column("price_list")
    private Integer priceList;
    @Column("product_id")
    private Long productId;
    @Column("priority")
    private Integer priority;
    @Column("price")
    private BigDecimal prices;
    @Column("curr")
    private String curr;

    public Price() {
    }

    public Price(Long id, Long brandId, BigDecimal prices, String curr, LocalDateTime startDate, LocalDateTime endDate) {
        this.id = id;
        this.brandId = brandId;
        this.prices = prices;
        this.curr = curr;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Long getId() {
        return id;
    }

    public Long getBrandId() {
        return brandId;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public Integer getPriceList() {
        return priceList;
    }

    public Long getProductId() {
        return productId;
    }

    public Integer getPriority() {
        return priority;
    }

    public BigDecimal getPrices() {
        return prices;
    }

    public String getCurr() {
        return curr;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public void setPriceList(Integer priceList) {
        this.priceList = priceList;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public void setPrices(BigDecimal prices) {
        this.prices = prices;
    }

    public void setCurr(String curr) {
        this.curr = curr;
    }
}