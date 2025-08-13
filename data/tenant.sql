CREATE TABLE "tenant" (
	"person_id"	INTEGER,
	"isrenting"	INTEGER NOT NULL,
	"isactive"	INTEGER NOT NULL,
	PRIMARY KEY("person_id"),
	FOREIGN KEY("person_id") REFERENCES "person"("id") ON DELETE CASCADE
);