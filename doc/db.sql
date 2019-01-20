CREATE TABLE "brand" (
	"id" serial NOT NULL,
	"name" character varying NOT NULL,
	"created" TIMESTAMP NOT NULL,
	"updated" TIMESTAMP NOT NULL,
	CONSTRAINT brand_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "model" (
	"id" serial NOT NULL,
	"name" character varying NOT NULL,
	"brand_id" integer NOT NULL,
	"created" TIMESTAMP NOT NULL,
	"updated" TIMESTAMP NOT NULL,
	CONSTRAINT model_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "car" (
	"id" serial NOT NULL,
	"number" character varying NOT NULL,
	"model_id" integer NOT NULL,
	"user_account_id" integer NOT NULL,
	"tariff_id" integer NOT NULL,
	"foto_id" integer NOT NULL,
	"created" TIMESTAMP NOT NULL,
	"updated" TIMESTAMP NOT NULL,
	CONSTRAINT car_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "foto" (
	"id" serial NOT NULL,
	"link" character varying NOT NULL,
	"created" TIMESTAMP NOT NULL,
	"updated" TIMESTAMP NOT NULL,
	CONSTRAINT foto_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "user_account" (
	"id" serial NOT NULL,
	"first_name" character varying NOT NULL,
	"last_name" character varying NOT NULL,
	"role" character varying NOT NULL,
	"email" character varying NOT NULL,
	"password" character varying NOT NULL,
	"created" TIMESTAMP NOT NULL,
	"updated" TIMESTAMP NOT NULL,
	CONSTRAINT user_account_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "transaction" (
	"id" serial NOT NULL,
	"user_account_id" integer NOT NULL,
	"value" DECIMAL NOT NULL,
	"description" character varying NOT NULL,
	"created" TIMESTAMP NOT NULL,
	"updated" TIMESTAMP NOT NULL,
	CONSTRAINT transaction_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "parking" (
	"id" serial NOT NULL,
	"name" character varying NOT NULL,
	"adress" character varying NOT NULL,
	"width" integer NOT NULL,
	"length" integer NOT NULL,
	"cost_per_day" DECIMAL NOT NULL,
	"status" character varying NOT NULL,
	"created" TIMESTAMP NOT NULL,
	"updated" TIMESTAMP NOT NULL,
	CONSTRAINT parking_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "place" (
	"id" serial NOT NULL,
	"name" character varying NOT NULL,
	"parking_id" integer NOT NULL,
	"car_id" integer,
	"user_account_id" bigint,
	"created" TIMESTAMP NOT NULL,
	"updated" TIMESTAMP NOT NULL,
	CONSTRAINT place_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "event" (
	"id" serial NOT NULL,
	"place_id" integer NOT NULL,
	"car_id" integer NOT NULL,
	"time_start" TIMESTAMP NOT NULL,
	"time_end" TIMESTAMP,
	"created" TIMESTAMP NOT NULL,
	"updated" TIMESTAMP NOT NULL,
	CONSTRAINT event_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "tariff" (
	"id" serial NOT NULL,
	"name" character varying NOT NULL,
	"cost_per_day" DECIMAL NOT NULL,
	"status" character varying NOT NULL,
	"created" TIMESTAMP NOT NULL,
	"updated" TIMESTAMP NOT NULL,
	CONSTRAINT tariff_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);




ALTER TABLE "model" ADD CONSTRAINT "model_fk0" FOREIGN KEY ("brand_id") REFERENCES "brand"("id");

ALTER TABLE "car" ADD CONSTRAINT "car_fk0" FOREIGN KEY ("model_id") REFERENCES "model"("id");
ALTER TABLE "car" ADD CONSTRAINT "car_fk1" FOREIGN KEY ("user_account_id") REFERENCES "user_account"("id");
ALTER TABLE "car" ADD CONSTRAINT "car_fk2" FOREIGN KEY ("tariff_id") REFERENCES "tariff"("id");
ALTER TABLE "car" ADD CONSTRAINT "car_fk3" FOREIGN KEY ("foto_id") REFERENCES "foto"("id");



ALTER TABLE "transaction" ADD CONSTRAINT "transaction_fk0" FOREIGN KEY ("user_account_id") REFERENCES "user_account"("id");


ALTER TABLE "place" ADD CONSTRAINT "place_fk0" FOREIGN KEY ("parking_id") REFERENCES "parking"("id");
ALTER TABLE "place" ADD CONSTRAINT "place_fk1" FOREIGN KEY ("car_id") REFERENCES "car"("id");
ALTER TABLE "place" ADD CONSTRAINT "place_fk2" FOREIGN KEY ("user_account_id") REFERENCES "user_account"("id");

ALTER TABLE "event" ADD CONSTRAINT "event_fk0" FOREIGN KEY ("place_id") REFERENCES "place"("id");
ALTER TABLE "event" ADD CONSTRAINT "event_fk1" FOREIGN KEY ("car_id") REFERENCES "car"("id");

