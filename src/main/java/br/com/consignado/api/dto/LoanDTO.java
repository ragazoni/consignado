package br.com.consignado.api.dto;

public class LoanDTO {

    private String userDocument;
    private double currentLoanValue;
    private String affiliation;
    private int totalInstallments;

    public String getUserDocument() {
        return userDocument;
    }

    public void setUserDocument(String userDocument) {
        this.userDocument = userDocument;
    }

    public double getCurrentLoanValue() {
        return currentLoanValue;
    }

    public void setCurrentLoanValue(double currentLoanValue) {
        if (Double.isInfinite(currentLoanValue) || Double.isNaN(currentLoanValue)) {
            this.currentLoanValue = 0.0;
            throw new IllegalArgumentException("Invalid value for currentLoanValue: " + currentLoanValue);
        } else {
            this.currentLoanValue = currentLoanValue;
        }
    }

    public String getAffiliation() {
        return affiliation;
    }

    public void setAffiliation(String affiliation) {
        this.affiliation = affiliation;
    }

    public int getTotalInstallments() {
        return totalInstallments;
    }

    public void setTotalInstallments(int totalInstallments) {
        if (Double.isInfinite(totalInstallments) || Double.isNaN(totalInstallments)) {
            this.currentLoanValue = 0.0;
            throw new IllegalArgumentException("Invalid value for totalInstallments: " + totalInstallments);
        } else {
            this.totalInstallments = totalInstallments;        }

    }

    @Override
    public String toString() {
        return "LoanDTO{" +
                "userDocument='" + userDocument + '\'' +
                ", currentLoanValue=" + currentLoanValue +
                ", affiliation='" + affiliation + '\'' +
                ", totalInstallments=" + totalInstallments +
                '}';
    }
}