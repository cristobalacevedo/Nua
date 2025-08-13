CREATE TABLE "tenant" (
	"person_id"	INTEGER,
	"hasrentals" INTEGER NOT NULL,
	"isactive" INTEGER NOT NULL,
	"aval_id" INTEGER,
	PRIMARY KEY("person_id"),
	FOREIGN KEY("person_id") REFERENCES "person"("id") ON DELETE CASCADE,
	FOREIGN KEY("aval_id") REFERENCES "aval"("id") ON DELETE CASCADE
);