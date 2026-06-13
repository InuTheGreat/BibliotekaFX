package pl.library.service;

import pl.library.dao.help.MonthView;
import pl.library.dao.utility.StatisticDao;

import java.util.List;

public class StatisticService {

    private final StatisticDao statisticDao = new StatisticDao();

    public int getAllUserCount() {

        return statisticDao.getUsersValue();
    }

    public int getAllBorrowCount() {

        return statisticDao.getBorrowValue();
    }

    public List<MonthView> getMonthResumeBorrow() {

        return statisticDao.getBorrowStatsByMonth();
    }
}
