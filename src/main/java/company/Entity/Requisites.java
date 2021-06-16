package company.Entity;

public class Requisites {
    public Long id;
    public String bank_name;
    public Integer mfo;
    public String IBAN;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBank_name() {
        return bank_name;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }

    public Integer getMfo() {
        return mfo;
    }

    public void setMfo(Integer mfo) {
        this.mfo = mfo;
    }

    public String getIBAN() {
        return IBAN;
    }

    public void setIBAN(String IBAN) {
        this.IBAN = IBAN;
    }
}
