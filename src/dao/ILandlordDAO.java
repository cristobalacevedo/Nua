package dao;

import model.Landlord;
import java.util.List;

/**
 * Interface for Landlord Data Access Object (DAO). Provides methods to perform
 * CRUD operations on Landlord entities.
 */
public interface ILandlordDAO {
    boolean save(Landlord landlord); // Create a new landlord
    List<Landlord> getAll(); // Retrieve all landlords
    Landlord getById(int id); // Retrieve a landlord by ID
    boolean update(Landlord landlord); // Update an existing landlord
    boolean delete(int id); // Delete a landlord by ID
}