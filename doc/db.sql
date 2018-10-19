CREATE TABLE "car" (
	"id" serial NOT NULL,
	"vin" character varying(20) NOT NULL UNIQUE,
	"created" TIMESTAMP NOT NULL,
	"updated" TIMESTAMP NOT NULL,
	"model_id" integer NOT NULL,
	"sold" BOOLEAN NOT NULL,
	"sold_date" TIMESTAMP,
	CONSTRAINT car_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "model" (
	"id" serial NOT NULL,
	"name" character varying(50) NOT NULL,
	"created" TIMESTAMP NOT NULL,
	"updated" TIMESTAMP NOT NULL,
	"brand_id" integer NOT NULL,
	CONSTRAINT model_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "brand" (
	"id" serial NOT NULL,
	"name" character varying(50) NOT NULL UNIQUE,
	"created" TIMESTAMP NOT NULL,
	"updated" TIMESTAMP NOT NULL,
	CONSTRAINT brand_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "model_info" (
	"id" integer NOT NULL,
	"description" character varying NOT NULL,
	"created" TIMESTAMP NOT NULL,
	"updated" TIMESTAMP NOT NULL,
	CONSTRAINT model_info_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "engine" (
	"id" serial NOT NULL,
	"type" character varying NOT NULL,
	"volume" integer NOT NULL,
	"title" character varying NOT NULL,
	"created" TIMESTAMP NOT NULL,
	"updated" TIMESTAMP NOT NULL,
	CONSTRAINT engine_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "model_2_engine" (
	"engine_id" integer NOT NULL,
	"model_id" integer NOT NULL
) WITH (
  OIDS=FALSE
);



ALTER TABLE "car" ADD CONSTRAINT "car_fk0" FOREIGN KEY ("model_id") REFERENCES "model"("id");

ALTER TABLE "model" ADD CONSTRAINT "model_fk0" FOREIGN KEY ("brand_id") REFERENCES "brand"("id");


ALTER TABLE "model_info" ADD CONSTRAINT "model_info_fk0" FOREIGN KEY ("id") REFERENCES "model"("id");


ALTER TABLE "model_2_engine" ADD CONSTRAINT "model_2_engine_fk0" FOREIGN KEY ("engine_id") REFERENCES "engine"("id");
ALTER TABLE "model_2_engine" ADD CONSTRAINT "model_2_engine_fk1" FOREIGN KEY ("model_id") REFERENCES "model"("id");
