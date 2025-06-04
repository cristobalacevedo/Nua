package controller;

import dao.ILandlordDAO;
import model.Landlord;

import java.util.List;

public class LandlordController {
    private ILandlordDAO dao;

    public LandlordController(ILandlordDAO dao) {
        this.dao = dao;
    }

    public void addLandlord(Landlord l) {
        if (dao.save(l)) {
            System.out.println("Propietario guardado."); // Success message - SPANISH
        } else {
            System.out.println("Error al guardar."); // Error message - SPANISH
        }
    }

    public void listLandlords() {
        List<Landlord> list = dao.getAll();
        for (Landlord l : list) {
            System.out.println(l);
        }
    }
}