CREATE VIEW all_available_properties AS
SELECT 
  CONCAT(pe.name, " ", pe.surname) AS Dueño,
  CASE 
    WHEN pp.name = "House" THEN "Casa" 
    WHEN pp.name = "Flat" THEN "Departamento"
    WHEN pp.name = "Parking" THEN "Estacionamiento"
    WHEN pp.name = "Storage" THEN "Bodega"
    WHEN pp.name = "Land" THEN "Parcela/Terreno"
    WHEN pp.name = "Office" THEN "Oficina"
  END AS Tipo,
  p.rol_sii AS ROL_SII,
  p.size AS Tamaño, 
  t.name AS Comuna,
  CONCAT(a.st_name, " ", a.num_1, " ", a.num_2) AS Dirección,
  CASE 
    WHEN p.available = 1 THEN "Disponible"
    WHEN p.available = 0 THEN "Arrendado"
  END AS Disponibilidad
FROM property p 
JOIN property_type pp ON p.property_type_id = pp.id 
JOIN landlord l ON p.landlord_id = l.person_id 
JOIN person pe ON l.person_id = pe.id 
JOIN address a ON p.address_id = a.id 
JOIN town t ON a.town_id = t.id;
