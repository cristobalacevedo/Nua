CREATE TABLE "parking" (
	"id"	INTEGER NOT NULL,
	"property_id"	INTEGER,
	"flat_id"	INTEGER,
	"office_id"	INTEGER,
	"incondo"	INTEGER,
	"condo_id"	INTEGER,
	PRIMARY KEY("id" AUTOINCREMENT),
	FOREIGN KEY("flat_id","property_id","condo_id") REFERENCES "flat"("id","property_id","condo_id") ON DELETE NO ACTION ON UPDATE NO ACTION,
	FOREIGN KEY("office_id","property_id","condo_id") REFERENCES "office"("id","property_id","condo_id") ON DELETE NO ACTION ON UPDATE NO ACTION,
	FOREIGN KEY("property_id") REFERENCES "property"("id") ON DELETE NO ACTION ON UPDATE NO ACTION
);