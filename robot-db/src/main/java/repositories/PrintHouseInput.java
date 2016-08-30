package repositories;

import dao.PrintHouseDAO;
import domain.PrintHouse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class PrintHouseInput {

    @Autowired
    PrintHouseDAO printHouseDAO;

    PrintHouse savePrintHouse(String name) {
        PrintHouse printHouse = printHouseDAO.findByNameIgnoreCase(name);
        if (printHouse == null) {
            printHouse = PrintHouse.builder().name(name).build();
            printHouseDAO.save(printHouse);
        }
        return printHouse;
    }
}
