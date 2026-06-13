package pl.library.service;

import pl.library.dao.utility.StatisticDao;

public class StatisticService {

    private final StatisticDao statisticDao = new StatisticDao();

    public int getAllUserCount() {

        return statisticDao.getUsersValue();
    }

    public int getAllBorrowCount() {

        return statisticDao.getBorrowValue();
    }
}
