package repositories;

import dao.PrintHouseDAO;
import domain.PrintHouse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Repository
@Transactional
class PrintHouseInput {

    @Autowired
    PrintHouseDAO printHouseDAO;
    private Map<String, PrintHouse> printHouses;

    @PostConstruct
    private void init() {
        printHouses = new HashMap<>();
        printHouseDAO.findAll().forEach(printHouse -> printHouses.put(printHouse.getName(), printHouse));
    }

    PrintHouse savePrintHouse(String name) {
        if (!printHouses.containsKey(name)) {
            return printHouses.get(name);
        }
        PrintHouse printHouse = PrintHouse.builder().name(name).build();
        printHouses.put(name, printHouse);
        printHouseDAO.save(printHouse);
        return printHouse;
    }
}
