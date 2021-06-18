package company.Entity;

import java.sql.Timestamp;

public class Account {
    public Long id;
    public Double balance;
    public User user;
    public Timestamp createTime;
    public String name;
    public Double limit;
    public String currency;
    public State state;

    public void replenishment(double sum){
        if(sum>=0) {
            this.balance += sum;
        }
    }

    public void withdrawal (double sum){
        if(sum>=0) {
            this.balance -= sum;
        }
    }

    public State getState() {

        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getLimit() {
        return limit;
    }

    public void setLimit(Double limit) {
        this.limit = limit;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", balance=" + balance +
                ", user=" + user +
                ", createTime=" + createTime +
                ", name='" + name + '\'' +
                ", limit=" + limit +
                ", currency='" + currency + '\'' +
                ", state=" + state +
                '}';
    }
}
