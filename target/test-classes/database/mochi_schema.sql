--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.24
-- Dumped by pg_dump version 9.6.24

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

ALTER TABLE ONLY mochi.tag_attr_lcl DROP CONSTRAINT tag_attr_lcl_tag_id_fkey;
ALTER TABLE ONLY mochi.tag_attr_lcl DROP CONSTRAINT tag_attr_lcl_lcl_cd_fkey;
ALTER TABLE ONLY mochi.supplier DROP CONSTRAINT supplier_role_id_fkey;
ALTER TABLE ONLY mochi.stock_on_hand DROP CONSTRAINT stock_on_hand_prd_id_fkey;
ALTER TABLE ONLY mochi.role DROP CONSTRAINT role_role_typ_id_fkey;
ALTER TABLE ONLY mochi.role DROP CONSTRAINT role_party_id_fkey;
ALTER TABLE ONLY mochi.promotion_product DROP CONSTRAINT promotion_regular_promotion_prm_id_fkey;
ALTER TABLE ONLY mochi.promotion_order DROP CONSTRAINT promotion_coupon_promotion_prm_id_fkey;
ALTER TABLE ONLY mochi.promotion_attr_lcl DROP CONSTRAINT promotion_attr_lcl_prm_id_fkey;
ALTER TABLE ONLY mochi.promotion_attr_lcl DROP CONSTRAINT promotion_attr_lcl_lcl_cd_fkey;
ALTER TABLE ONLY mochi.product DROP CONSTRAINT product_typ_id_product_type_typ_id_fkey;
ALTER TABLE ONLY mochi.product_tag DROP CONSTRAINT product_tag_tag_id_tag_tag_id_fkey;
ALTER TABLE ONLY mochi.product_tag DROP CONSTRAINT product_tag_prd_id_product_prd_id_fkey;
ALTER TABLE ONLY mochi.product DROP CONSTRAINT product_sts_id_product_status_sts_id_fkey;
ALTER TABLE ONLY mochi.product_shipping DROP CONSTRAINT product_shipping_prd_id_fkey;
ALTER TABLE ONLY mochi.product_shipping_attr_lcl DROP CONSTRAINT product_shipping_attr_lcl_prd_id_fkey;
ALTER TABLE ONLY mochi.product_shipping_attr_lcl DROP CONSTRAINT product_shipping_attr_lcl_lcl_cd_fkey;
ALTER TABLE ONLY mochi.product_category DROP CONSTRAINT product_category_prd_id_product_prd_id_fkey;
ALTER TABLE ONLY mochi.product_category DROP CONSTRAINT product_category_cat_id_category_cat_id_fkey;
ALTER TABLE ONLY mochi.product_basic DROP CONSTRAINT product_basic_prd_id_fkey;
ALTER TABLE ONLY mochi.product_attr_lcl DROP CONSTRAINT product_attr_lcl_prd_id_fkey;
ALTER TABLE ONLY mochi.product_attr_lcl DROP CONSTRAINT product_attr_lcl_lcl_cd_fkey;
ALTER TABLE ONLY mochi.price DROP CONSTRAINT price_product_id_fkey;
ALTER TABLE ONLY mochi.price DROP CONSTRAINT price_currency_id_fkey;
ALTER TABLE ONLY mochi.person DROP CONSTRAINT person_person_id_fkey;
ALTER TABLE ONLY mochi.party DROP CONSTRAINT party_pty_typ_id_fkey;
ALTER TABLE ONLY mochi.organisation DROP CONSTRAINT organisation_org_id_fkey;
ALTER TABLE ONLY mochi."order" DROP CONSTRAINT orders_party_id_fkey;
ALTER TABLE ONLY mochi.order_line DROP CONSTRAINT order_line_product_id_fkey;
ALTER TABLE ONLY mochi.order_line DROP CONSTRAINT order_line_order_id_fkey;
ALTER TABLE ONLY mochi.inventory_transaction DROP CONSTRAINT inventory_transaction_inv_trx_typ_id_inventory_transaction_type;
ALTER TABLE ONLY mochi.inventory_transaction DROP CONSTRAINT inventory_transaction_inv_pty_id_supplier_pty_id;
ALTER TABLE ONLY mochi.inventory_transaction DROP CONSTRAINT inventory_transaction_inv_prd_id_product_prd_id;
ALTER TABLE ONLY mochi.inventory_transaction DROP CONSTRAINT inventory_transaction_inv_loc_id_inventory_location_inv_loc_id;
ALTER TABLE ONLY mochi.inventory_transaction DROP CONSTRAINT inventory_transaction_ccy_id_currency_ccy_id;
ALTER TABLE ONLY mochi.department_attr_lcl DROP CONSTRAINT department_attr_lcl_lcl_cd_fkey;
ALTER TABLE ONLY mochi.department_attr_lcl DROP CONSTRAINT department_attr_lcl_dept_id_fkey;
ALTER TABLE ONLY mochi.customer DROP CONSTRAINT customer_role_id_fkey;
ALTER TABLE ONLY mochi.category_product DROP CONSTRAINT category_product_cat_id_category_cat_id_fkey;
ALTER TABLE ONLY mochi.category_brand DROP CONSTRAINT category_brand_cat_id_category_cat_id_fkey;
ALTER TABLE ONLY mochi.category_attr_lcl DROP CONSTRAINT category_attr_lcl_lcl_cd_fkey;
ALTER TABLE ONLY mochi.category_attr_lcl DROP CONSTRAINT category_attr_lcl_cat_id_fkey;
ALTER TABLE ONLY mochi.brand_category DROP CONSTRAINT brand_category_cat_id_category_cat_id_fkey;
ALTER TABLE ONLY mochi.brand_category DROP CONSTRAINT brand_category_bnd_id_brand_bnd_id_fkey;
ALTER TABLE ONLY mochi.brand_attr_lcl DROP CONSTRAINT brand_attr_lcl_lcl_cd_fkey;
ALTER TABLE ONLY mochi.brand_attr_lcl DROP CONSTRAINT brand_attr_lcl_bnd_id_fkey;
ALTER TABLE ONLY mochi.bag DROP CONSTRAINT bag_pty_id_party_pty_id_fkey;
ALTER TABLE ONLY mochi.bag DROP CONSTRAINT bag_prm_id_promotion_order_prm_id_fkey;
ALTER TABLE ONLY mochi.bag_item DROP CONSTRAINT bag_item_sts_id_bag_item_sts_bag_item_sts_id_fkey;
ALTER TABLE ONLY mochi.bag_item_disc DROP CONSTRAINT bag_item_disc_bag_item_id_bag_item_bag_item_id_fkey;
ALTER TABLE ONLY mochi.bag_item DROP CONSTRAINT bag_item_bag_id_bag_bag_id_fkey;
ALTER TABLE ONLY mochi.address DROP CONSTRAINT address_pty_id_party_pty_id_fkey;
ALTER TABLE ONLY mochi.address DROP CONSTRAINT address_addr_typ_id_address_type_addr_typ_id_fkey;
ALTER TABLE ONLY mochi.accessories_attr_lcl DROP CONSTRAINT accessories_attr_lcl_lcl_cd_fkey;
DROP INDEX mochi.role_role_typ_id_role_start_dttm_party_id_key;
DROP INDEX mochi.fki_promotion_attr_lcl_prm_id_fkey;
DROP INDEX mochi.fki_product_shipping_attr_lcl_prd_id_fkey;
DROP INDEX mochi.fki_product_attr_lcl_prd_id_fkey;
ALTER TABLE ONLY mochi.tag_attr_lcl DROP CONSTRAINT uc_tag_lcl;
ALTER TABLE ONLY mochi.tag_attr_lcl DROP CONSTRAINT uc_tag_desc;
ALTER TABLE ONLY mochi.tag DROP CONSTRAINT uc_tag_cd;
ALTER TABLE ONLY mochi.product DROP CONSTRAINT uc_product_upc_cd;
ALTER TABLE ONLY mochi.product_tag DROP CONSTRAINT uc_product_tag;
ALTER TABLE ONLY mochi.product_category DROP CONSTRAINT uc_product_category;
ALTER TABLE ONLY mochi.promotion_type DROP CONSTRAINT uc_prm_typ_cd;
ALTER TABLE ONLY mochi.promotion_mechanic DROP CONSTRAINT uc_prm_mec_cd;
ALTER TABLE ONLY mochi.promotion_level DROP CONSTRAINT uc_prm_lvl_cd;
ALTER TABLE ONLY mochi.promotion_attr_lcl DROP CONSTRAINT uc_prm_lcl_1;
ALTER TABLE ONLY mochi.promotion DROP CONSTRAINT uc_prm_cd;
ALTER TABLE ONLY mochi.department DROP CONSTRAINT uc_prd_typ_cd;
ALTER TABLE ONLY mochi.product_status DROP CONSTRAINT uc_prd_sts_cd;
ALTER TABLE ONLY mochi.product_rating DROP CONSTRAINT uc_prd_rat;
ALTER TABLE ONLY mochi.product_shipping_attr_lcl DROP CONSTRAINT uc_prd_lcl_2;
ALTER TABLE ONLY mochi.product_attr_lcl DROP CONSTRAINT uc_prd_lcl_1;
ALTER TABLE ONLY mochi.stock_on_hand DROP CONSTRAINT uc_prd_id;
ALTER TABLE ONLY mochi.category_type DROP CONSTRAINT uc_cat_typ_cd;
ALTER TABLE ONLY mochi.category_attr_lcl DROP CONSTRAINT uc_cat_lcl;
ALTER TABLE ONLY mochi.category_attr_lcl DROP CONSTRAINT uc_cat_desc;
ALTER TABLE ONLY mochi.category DROP CONSTRAINT uc_cat_cd;
ALTER TABLE ONLY mochi.brand_category DROP CONSTRAINT uc_brand_category;
ALTER TABLE ONLY mochi.brand_attr_lcl DROP CONSTRAINT uc_bnd_lcl;
ALTER TABLE ONLY mochi.brand_attr_lcl DROP CONSTRAINT uc_bnd_desc_lcl_cd;
ALTER TABLE ONLY mochi.brand DROP CONSTRAINT uc_bnd_cd;
ALTER TABLE ONLY mochi.bag_item_status DROP CONSTRAINT uc_bag_sts_cd;
ALTER TABLE ONLY mochi.bag_item DROP CONSTRAINT uc_bag_item;
ALTER TABLE ONLY mochi.tag DROP CONSTRAINT tag_pkey;
ALTER TABLE ONLY mochi.tag_attr_lcl DROP CONSTRAINT tag_attr_lcl_pkey;
ALTER TABLE ONLY mochi.supplier DROP CONSTRAINT supplier_pkey;
ALTER TABLE ONLY mochi.stock_on_hand DROP CONSTRAINT stock_on_hand_pkey;
ALTER TABLE ONLY mochi.role_type DROP CONSTRAINT role_type_rle_typ_desc_key;
ALTER TABLE ONLY mochi.role_type DROP CONSTRAINT role_type_pkey;
ALTER TABLE ONLY mochi.role DROP CONSTRAINT role_pty_id_key;
ALTER TABLE ONLY mochi.role DROP CONSTRAINT role_pkey;
ALTER TABLE ONLY mochi.rating DROP CONSTRAINT rating_pkey;
ALTER TABLE ONLY mochi.promotion_type DROP CONSTRAINT promotion_type_pkey;
ALTER TABLE ONLY mochi.promotion_product DROP CONSTRAINT promotion_regular_pkey;
ALTER TABLE ONLY mochi.promotion DROP CONSTRAINT promotion_pkey;
ALTER TABLE ONLY mochi.promotion_mechanic DROP CONSTRAINT promotion_mechanic_pkey;
ALTER TABLE ONLY mochi.promotion_level DROP CONSTRAINT promotion_level_pkey;
ALTER TABLE ONLY mochi.promotion_order DROP CONSTRAINT promotion_coupon_pkey;
ALTER TABLE ONLY mochi.promotion_attr_lcl DROP CONSTRAINT promotion_attr_lcl_pkey;
ALTER TABLE ONLY mochi.product_tag DROP CONSTRAINT product_tag_pkey;
ALTER TABLE ONLY mochi.product_status DROP CONSTRAINT product_status_pkey;
ALTER TABLE ONLY mochi.product_shipping DROP CONSTRAINT product_shipping_pkey;
ALTER TABLE ONLY mochi.product_shipping_attr_lcl DROP CONSTRAINT product_shipping_attr_lcl_pkey;
ALTER TABLE ONLY mochi.product_rating DROP CONSTRAINT product_rating_pkey;
ALTER TABLE ONLY mochi.product_promotion DROP CONSTRAINT product_promotion_pkey;
ALTER TABLE ONLY mochi.product DROP CONSTRAINT product_pkey;
ALTER TABLE ONLY mochi.product_category DROP CONSTRAINT product_category_pkey;
ALTER TABLE ONLY mochi.product_basic DROP CONSTRAINT product_basic_pkey;
ALTER TABLE ONLY mochi.product_attr_lcl DROP CONSTRAINT product_attr_lcl_pkey;
ALTER TABLE ONLY mochi.price_type DROP CONSTRAINT price_type_pkey;
ALTER TABLE ONLY mochi.price DROP CONSTRAINT price_pkey;
ALTER TABLE ONLY mochi.department_attr_lcl DROP CONSTRAINT prd_id_lcl_cd_3;
ALTER TABLE ONLY mochi.accessories_attr_lcl DROP CONSTRAINT prd_id_lcl_cd_1;
ALTER TABLE ONLY mochi.locale DROP CONSTRAINT pk_locale;
ALTER TABLE ONLY mochi.person DROP CONSTRAINT person_psn_id_key;
ALTER TABLE ONLY mochi.party_type DROP CONSTRAINT party_type_pty_typ_desc_key;
ALTER TABLE ONLY mochi.party_type DROP CONSTRAINT party_type_pkey;
ALTER TABLE ONLY mochi.party DROP CONSTRAINT party_pkey;
ALTER TABLE ONLY mochi.organisation DROP CONSTRAINT organisation_org_id_key;
ALTER TABLE ONLY mochi."order" DROP CONSTRAINT orders_pty_id_key;
ALTER TABLE ONLY mochi."order" DROP CONSTRAINT orders_pkey;
ALTER TABLE ONLY mochi.order_line DROP CONSTRAINT order_line_pkey;
ALTER TABLE ONLY mochi.order_line DROP CONSTRAINT order_line_ord_id_key;
ALTER TABLE ONLY mochi.inventory_transaction_type DROP CONSTRAINT inventory_transaction_type_pkey;
ALTER TABLE ONLY mochi.inventory_transaction DROP CONSTRAINT inventory_transaction_pkey;
ALTER TABLE ONLY mochi.inventory_location DROP CONSTRAINT inventory_location_pkey;
ALTER TABLE ONLY mochi.discount_type DROP CONSTRAINT discount_type_pkey;
ALTER TABLE ONLY mochi.discount DROP CONSTRAINT discount_pkey;
ALTER TABLE ONLY mochi.department DROP CONSTRAINT department_pkey;
ALTER TABLE ONLY mochi.department_attr_lcl DROP CONSTRAINT department_attr_lcl_pkey;
ALTER TABLE ONLY mochi.customer DROP CONSTRAINT customer_pkey;
ALTER TABLE ONLY mochi.customer DROP CONSTRAINT customer_cst_id_key;
ALTER TABLE ONLY mochi.currency DROP CONSTRAINT currency_pkey;
ALTER TABLE ONLY mochi.category_type DROP CONSTRAINT category_type_pkey;
ALTER TABLE ONLY mochi.category_promotion DROP CONSTRAINT category_promotion_pkey;
ALTER TABLE ONLY mochi.category_product DROP CONSTRAINT category_product_pkey;
ALTER TABLE ONLY mochi.category DROP CONSTRAINT category_pkey;
ALTER TABLE ONLY mochi.category_brand DROP CONSTRAINT category_brand_pkey;
ALTER TABLE ONLY mochi.category_attr_lcl DROP CONSTRAINT category_attr_lcl_pkey;
ALTER TABLE ONLY mochi.brand_promotion DROP CONSTRAINT brand_promotion_pkey;
ALTER TABLE ONLY mochi.brand DROP CONSTRAINT brand_pkey;
ALTER TABLE ONLY mochi.brand_category DROP CONSTRAINT brand_category_pkey;
ALTER TABLE ONLY mochi.brand_attr_lcl DROP CONSTRAINT brand_attr_lcl_pkey;
ALTER TABLE ONLY mochi.bag_item_status DROP CONSTRAINT bag_status_pkey;
ALTER TABLE ONLY mochi.bag DROP CONSTRAINT bag_pkey;
ALTER TABLE ONLY mochi.bag_item DROP CONSTRAINT bag_item_pkey;
ALTER TABLE ONLY mochi.bag_item_disc DROP CONSTRAINT bag_item_disc_pkey;
ALTER TABLE ONLY mochi.address_type DROP CONSTRAINT address_type_pkey;
ALTER TABLE ONLY mochi.address DROP CONSTRAINT address_pkey;
ALTER TABLE ONLY mochi.accessories_attr_lcl DROP CONSTRAINT accessories_attr_lcl_pkey;
ALTER TABLE mochi.role_type ALTER COLUMN rle_typ_id DROP DEFAULT;
ALTER TABLE mochi.party_type ALTER COLUMN pty_typ_id DROP DEFAULT;
ALTER TABLE mochi.party ALTER COLUMN pty_id DROP DEFAULT;
DROP TABLE mochi.tag_attr_lcl;
DROP SEQUENCE mochi.tag_attr_lcl_tag_lcl_id_seq;
DROP TABLE mochi.tag;
DROP SEQUENCE mochi.tag_tag_id_seq;
DROP TABLE mochi.supplier;
DROP SEQUENCE mochi.supplier_sup_num_seq;
DROP SEQUENCE mochi.supplier_rle_id_seq;
DROP TABLE mochi.stock_on_hand;
DROP SEQUENCE mochi.stock_on_hand_soh_id_seq;
DROP SEQUENCE mochi.role_type_rle_typ_id_seq;
DROP TABLE mochi.role_type;
DROP TABLE mochi.role;
DROP SEQUENCE mochi.role_rle_id_seq;
DROP TABLE mochi.rating;
DROP SEQUENCE mochi.rating_rat_id_seq;
DROP TABLE mochi.promotion_type;
DROP SEQUENCE mochi.promotion_type_prm_typ_id_seq;
DROP TABLE mochi.promotion_product;
DROP SEQUENCE mochi.promotion_product_prm_id_seq;
DROP TABLE mochi.promotion_order;
DROP SEQUENCE mochi.promotion_order_prm_id_seq;
DROP TABLE mochi.promotion_mechanic;
DROP SEQUENCE mochi.promotion_mechanic_prm_mec_id_seq;
DROP TABLE mochi.promotion_level;
DROP SEQUENCE mochi.promotion_level_prm_lvl_id_seq;
DROP TABLE mochi.promotion_attr_lcl;
DROP SEQUENCE mochi.promotion_attr_lcl_prm_lcl_id_seq;
DROP TABLE mochi.promotion;
DROP SEQUENCE mochi.promotion_prm_id_seq;
DROP TABLE mochi.product_tag;
DROP SEQUENCE mochi.product_tag_prd_tag_id_seq;
DROP TABLE mochi.product_supplier;
DROP TABLE mochi.product_status;
DROP SEQUENCE mochi.product_status_prd_sts_id_seq;
DROP TABLE mochi.product_shipping_attr_lcl;
DROP SEQUENCE mochi.product_shipping_attr_lcl_prd_lcl_id_seq;
DROP TABLE mochi.product_shipping;
DROP SEQUENCE mochi.product_shipping_prd_id_seq;
DROP TABLE mochi.product_rating;
DROP SEQUENCE mochi.product_rating_prd_rat_id_seq;
DROP TABLE mochi.product_promotion;
DROP SEQUENCE mochi.product_promotion_prm_id_seq;
DROP SEQUENCE mochi.product_promotion_prd_id_seq;
DROP TABLE mochi.product_category;
DROP SEQUENCE mochi.product_category_prd_cat_id_seq;
DROP TABLE mochi.product_basic;
DROP SEQUENCE mochi.product_basic_prd_id_seq;
DROP TABLE mochi.product_attr_lcl;
DROP SEQUENCE mochi.product_attr_lcl_prd_lcl_id_seq;
DROP TABLE mochi.product;
DROP SEQUENCE mochi.product_prd_id_seq;
DROP TABLE mochi.price_type;
DROP SEQUENCE mochi.price_type_prc_typ_id_seq;
DROP TABLE mochi.price;
DROP SEQUENCE mochi.price_prc_id_seq;
DROP TABLE mochi.person;
DROP SEQUENCE mochi.party_type_pty_typ_id_seq;
DROP TABLE mochi.party_type;
DROP SEQUENCE mochi.party_pty_id_seq;
DROP TABLE mochi.party;
DROP TABLE mochi.organisation;
DROP TABLE mochi.order_line;
DROP SEQUENCE mochi.order_line_prd_id_seq;
DROP SEQUENCE mochi.order_line_ord_lne_no_seq;
DROP SEQUENCE mochi.order_line_ord_id_seq;
DROP TABLE mochi."order";
DROP SEQUENCE mochi.order_ord_id_seq;
DROP TABLE mochi.locale;
DROP SEQUENCE mochi.locale_lcl_cd_seq;
DROP TABLE mochi.inventory_transaction_type;
DROP SEQUENCE mochi.inventory_transaction_type_inv_trx_typ_id_seq;
DROP TABLE mochi.inventory_transaction;
DROP SEQUENCE mochi.inventory_transaction_inv_trx_id_seq;
DROP TABLE mochi.inventory_location;
DROP SEQUENCE mochi.inventory_location_inv_loc_id_seq;
DROP TABLE mochi.discount_type;
DROP SEQUENCE mochi.discount_type_dis_typ_id_seq;
DROP TABLE mochi.discount;
DROP SEQUENCE mochi.discount_dis_id_seq;
DROP TABLE mochi.department_attr_lcl;
DROP SEQUENCE mochi.department_attr_lcl_dept_lcl_id_seq;
DROP TABLE mochi.department;
DROP SEQUENCE mochi.department_dept_id_seq;
DROP TABLE mochi.customer;
DROP SEQUENCE mochi.customer_rle_id_seq;
DROP SEQUENCE mochi.customer_cst_num_seq;
DROP TABLE mochi.currency;
DROP SEQUENCE mochi.currency_ccy_id_seq;
DROP TABLE mochi.category_type;
DROP SEQUENCE mochi.category_type_cat_typ_id_seq;
DROP TABLE mochi.category_promotion;
DROP SEQUENCE mochi.category_promotion_prm_id_seq;
DROP SEQUENCE mochi.category_promotion_cat_id_seq;
DROP TABLE mochi.category_product;
DROP SEQUENCE mochi.category_product_cat_id_seq;
DROP TABLE mochi.category_brand;
DROP SEQUENCE mochi.category_brand_cat_id_seq;
DROP TABLE mochi.category_attr_lcl;
DROP SEQUENCE mochi.category_attr_lcl_cat_lcl_id_seq;
DROP TABLE mochi.category;
DROP SEQUENCE mochi.category_cat_id_seq;
DROP TABLE mochi.brand_promotion;
DROP SEQUENCE mochi.brand_promotion_prm_id_seq;
DROP SEQUENCE mochi.brand_promotion_bnd_id_seq;
DROP TABLE mochi.brand_category;
DROP SEQUENCE mochi.brand_category_bnd_cat_id_seq;
DROP SEQUENCE mochi.brand_attr_lcl_bnd_id_seq;
DROP TABLE mochi.brand_attr_lcl;
DROP SEQUENCE mochi.brand_attr_lcl_bnd_lcl_id_seq;
DROP TABLE mochi.brand;
DROP SEQUENCE mochi.brand_bnd_id_seq;
DROP TABLE mochi.bag_item_status;
DROP SEQUENCE mochi.bag_item_status_bag_item_sts_id_seq;
DROP TABLE mochi.bag_item_disc;
DROP SEQUENCE mochi.bag_item_disc_bag_item_disc_id_seq;
DROP TABLE mochi.bag_item;
DROP SEQUENCE mochi.bag_item_bag_item_id_seq;
DROP TABLE mochi.bag;
DROP SEQUENCE mochi.bag_bag_id_seq;
DROP TABLE mochi.address_type;
DROP SEQUENCE mochi.address_type_addr_typ_id_seq;
DROP TABLE mochi.address;
DROP SEQUENCE mochi.address_addr_id_seq;
DROP TABLE mochi.accessories_attr_lcl;
DROP SEQUENCE mochi.accessories_attr_lcl_prd_lcl_id_seq;
DROP FUNCTION mochi.load_shipping_products();
DROP FUNCTION mochi.load_postage_type();
DROP FUNCTION mochi.load_postage_destination();
DROP FUNCTION mochi.ft_product_categories(text, text);
DROP FUNCTION mochi.ft_categories(text);
DROP FUNCTION mochi.ft_brand_categories(text, text);
DROP SCHEMA mochi;
--
-- Name: mochi; Type: SCHEMA; Schema: -; Owner: mochidb_owner
--

CREATE SCHEMA mochi;


ALTER SCHEMA mochi OWNER TO mochidb_owner;

--
-- Name: ft_brand_categories(text, text); Type: FUNCTION; Schema: mochi; Owner: mochidb_owner
--

CREATE FUNCTION mochi.ft_brand_categories(text, text) RETURNS TABLE(cat_id bigint, cat_cd text, cat_lvl bigint, hir_id bigint, cat_prnt_id bigint, cat_typ_id bigint, product_count bigint, child_cat_count bigint, max_price numeric)
    LANGUAGE sql
    AS '































 SELECT p.cat_id,































    p.cat_cd,































    p.cat_lvl,































    p.hir_id,































    p.cat_typ_id,































    p.cat_prnt_id,































    count(DISTINCT prd.upc_cd) AS product_count,































    count(DISTINCT cc.cat_cd) AS child_cat_count,































    coalesce(































    max(CASE































	WHEN pt.prc_typ_cd = ''MKD01''































	THEN prc.prc_val































	END),































    max(CASE































	WHEN pt.prc_typ_cd = ''RET01''































	THEN prc.prc_val































	END)) as max_price































   FROM mochi.category p































     JOIN LATERAL mochi.ft_categories(p.cat_cd::text) cc(cat_id, cat_cd, cat_prnt_id, cat_typ_id) ON 1 = 1































     LEFT JOIN mochi.brand_category pc ON cc.cat_id = pc.cat_id































     LEFT JOIN mochi.brand bnd ON pc.bnd_id = bnd.bnd_id































     LEFT JOIN mochi.product prd ON pc.bnd_id = prd.bnd_id































     LEFT JOIN mochi.price prc ON prd.prd_id = prc.prd_id AND now() between prc.prc_st_dt and prc.prc_en_dt































     LEFT JOIN mochi.currency curr ON prc.ccy_id = curr.ccy_id 































     LEFT JOIN mochi.price_type pt ON prc.prc_typ_id = pt.prc_typ_id 































  WHERE cc.cat_typ_id = 2































  AND p.cat_cd = $1































  AND ccy_cd = $2































  GROUP BY p.cat_id, p.cat_cd, p.cat_lvl, p.hir_id, p.cat_typ_id, p.cat_prnt_id;































';


ALTER FUNCTION mochi.ft_brand_categories(text, text) OWNER TO mochidb_owner;

--
-- Name: ft_categories(text); Type: FUNCTION; Schema: mochi; Owner: mochidb_owner
--

CREATE FUNCTION mochi.ft_categories(text) RETURNS TABLE(cat_id bigint, cat_cd text, cat_prnt_id bigint, cat_typ_id bigint)
    LANGUAGE sql
    AS '























WITH RECURSIVE 























    -- starting node(s)























    starting (cat_id, cat_cd, cat_prnt_id, cat_typ_id) AS























    (























      SELECT t.cat_id, t.cat_cd, t.cat_prnt_id, t.cat_typ_id























      FROM mochi.category AS t























      WHERE t.cat_cd = $1        























    ),























    descendants (cat_id, cat_cd, cat_prnt_id, cat_typ_id) AS























    (























      SELECT t.cat_id, t.cat_cd, t.cat_prnt_id, t.cat_typ_id























      FROM mochi.category AS t























      WHERE t.cat_cd = $1























      UNION ALL























      SELECT t.cat_id, t.cat_cd, t.cat_prnt_id, t.cat_typ_id























      FROM mochi.category AS t 























		JOIN descendants AS d 























		ON t.cat_prnt_id = d.cat_id























    )















































SELECT 	descendants.cat_id,























		descendants.cat_cd,























		descendants.cat_prnt_id,























		descendants.cat_typ_id























FROM  starting 























		cross join descendants ';


ALTER FUNCTION mochi.ft_categories(text) OWNER TO mochidb_owner;

--
-- Name: ft_product_categories(text, text); Type: FUNCTION; Schema: mochi; Owner: mochidb_owner
--

CREATE FUNCTION mochi.ft_product_categories(text, text) RETURNS TABLE(cat_id bigint, cat_cd text, cat_lvl bigint, hir_id bigint, cat_prnt_id bigint, cat_typ_id bigint, product_count bigint, child_cat_count bigint, max_price numeric)
    LANGUAGE sql
    AS '































 SELECT p.cat_id,































    p.cat_cd,































    p.cat_lvl,































    p.hir_id,































    p.cat_typ_id,































    p.cat_prnt_id,































    count(DISTINCT prd.upc_cd) AS product_count,































    count(DISTINCT cc.cat_cd) AS child_cat_count,































    coalesce(































    max(CASE































	WHEN pt.prc_typ_cd = ''MKD01''































	THEN prc.prc_val































	END),































    max(CASE































	WHEN pt.prc_typ_cd = ''RET01''































	THEN prc.prc_val































	END)) as max_price































   FROM mochi.category p































     JOIN LATERAL mochi.ft_categories(p.cat_cd::text) cc(cat_id, cat_cd, cat_prnt_id, cat_typ_id) ON 1 = 1































     LEFT JOIN mochi.product_category pc ON cc.cat_id = pc.cat_id































     LEFT JOIN mochi.product prd ON pc.prd_id = prd.prd_id































     LEFT JOIN mochi.price prc ON prd.prd_id = prc.prd_id AND now() between prc.prc_st_dt and prc.prc_en_dt































     LEFT JOIN mochi.currency curr ON prc.ccy_id = curr.ccy_id 































     LEFT JOIN mochi.price_type pt ON prc.prc_typ_id = pt.prc_typ_id 































  WHERE cc.cat_typ_id = 1































  AND p.cat_cd = $1































  AND ccy_cd = $2































  GROUP BY p.cat_id, p.cat_cd, p.cat_lvl, p.hir_id, p.cat_typ_id, p.cat_prnt_id;































';


ALTER FUNCTION mochi.ft_product_categories(text, text) OWNER TO mochidb_owner;

--
-- Name: load_postage_destination(); Type: FUNCTION; Schema: mochi; Owner: mochidb_owner
--

CREATE FUNCTION mochi.load_postage_destination() RETURNS integer
    LANGUAGE plpgsql
    AS '

declare 

record_count integer = 0;

begin

	ALTER TABLE mochi.postage_destination_attr_lcl

    DROP CONSTRAINT IF EXISTS postage_destination_attr_lcl_pst_dst_id_fkey;



	ALTER TABLE mochi.product_shipping

    DROP CONSTRAINT IF EXISTS  product_shipping_postage_destination_pst_dst_id_fkey;



	TRUNCATE TABLE mochi.postage_destination_attr_lcl;



	TRUNCATE TABLE mochi.postage_destination;



	INSERT INTO mochi.postage_destination(pst_dst_id, pst_dst_cd, pst_zne_cd)

	SELECT pst_dst_id, destinationcode, zonecode

	FROM mochi.vw_postage_destination;

	

	INSERT INTO mochi.postage_destination_attr_lcl(pst_dst_lcl_id, pst_dst_id, pst_dst_desc, lcl_cd)

	SELECT pst_dst_lcl_id, pst_dst_id, pst_dst_desc, lcl_cd

	FROM mochi.vw_postage_destination_attr_lcl;



	ALTER TABLE mochi.product_shipping

    ADD CONSTRAINT product_shipping_postage_destination_pst_dst_id_fkey FOREIGN KEY (pst_dst_id)

    REFERENCES mochi.postage_destination (pst_dst_id) MATCH SIMPLE

    ON UPDATE RESTRICT

    ON DELETE RESTRICT;

	

	ALTER TABLE mochi.postage_destination_attr_lcl

    ADD CONSTRAINT postage_destination_attr_lcl_pst_dst_id_fkey FOREIGN KEY (pst_dst_id)

    REFERENCES mochi.postage_destination (pst_dst_id) MATCH SIMPLE

    ON UPDATE NO ACTION

    ON DELETE NO ACTION;



	RETURN 1;

end;

';


ALTER FUNCTION mochi.load_postage_destination() OWNER TO mochidb_owner;

--
-- Name: load_postage_type(); Type: FUNCTION; Schema: mochi; Owner: mochidb_owner
--

CREATE FUNCTION mochi.load_postage_type() RETURNS integer
    LANGUAGE plpgsql
    AS '

declare 

record_count integer = 0;

begin

	ALTER TABLE mochi.postage_type_attr_lcl

    DROP CONSTRAINT IF EXISTS postage_type_attr_lcl_pst_typ_id_fkey;



	ALTER TABLE mochi.product_shipping

    DROP CONSTRAINT IF EXISTS  product_shipping_postage_type_pst_typ_id_fkey;



	TRUNCATE TABLE mochi.postage_type_attr_lcl;



	TRUNCATE TABLE mochi.postage_type;



	INSERT INTO mochi.postage_type(pst_typ_id, pst_typ_cd)

	SELECT pst_typ_id, pst_typ_cd

	FROM mochi.vw_postage_type;

	

	INSERT INTO mochi.postage_type_attr_lcl(pst_typ_lcl_id, pst_typ_id, pst_typ_desc, lcl_cd)

	SELECT pst_typ_lcl_id, pst_typ_id, pst_typ_desc, lcl_cd

	FROM mochi.vw_postage_type_attr_lcl;



	ALTER TABLE mochi.product_shipping

    ADD CONSTRAINT product_shipping_postage_type_pst_typ_id_fkey FOREIGN KEY (pst_typ_id)

    REFERENCES mochi.postage_type (pst_typ_id) MATCH SIMPLE

    ON UPDATE RESTRICT

    ON DELETE RESTRICT;

	

	ALTER TABLE mochi.postage_type_attr_lcl

    ADD CONSTRAINT postage_type_attr_lcl_pst_typ_id_fkey FOREIGN KEY (pst_typ_id)

    REFERENCES mochi.postage_type (pst_typ_id) MATCH SIMPLE

    ON UPDATE NO ACTION

    ON DELETE NO ACTION;



	RETURN 1;

end;

';


ALTER FUNCTION mochi.load_postage_type() OWNER TO mochidb_owner;

--
-- Name: load_shipping_products(); Type: FUNCTION; Schema: mochi; Owner: mochidb_owner
--

CREATE FUNCTION mochi.load_shipping_products() RETURNS integer
    LANGUAGE plpgsql
    AS '

declare 

record_count integer = 0;

begin



INSERT INTO mochi.product(prd_id, upc_cd, prd_crtd_dt, bnd_id, dept_id, prd_sts_id)

SELECT 	

		nextval(''mochi."hibernate_sequence"''),

		_product_upc_code, 

		to_date(_product_created_date, ''YYYY-MM-DD HH:MM:SS'') as _product_created_date,

		42 as bnd_id,

		1 dept_id,

		1 prd_sts_id

FROM yahoo.vw_export_shipping_master m;



insert into mochi.product_attr_lcl(prd_lcl_id, prd_id, prd_desc, prd_img_pth, lcl_cd, prd_lng_desc)

select nextval(''mochi."hibernate_sequence"''),

		p.prd_id,

		trim(m._product_description_en),

		null,

		''en-GB'',

		trim(m._product_description_en)

from mochi.product p

	left join yahoo.vw_export_shipping_master m

		on p.upc_cd = m._product_upc_code

where p.dept_id = 1;







insert into mochi.product_attr_lcl(prd_lcl_id, prd_id, prd_desc, prd_img_pth, lcl_cd, prd_lng_desc)

select nextval(''mochi."hibernate_sequence"''),

		p.prd_id,

		trim(m._product_description_hk),

		null,

		''zh-HK'',

		trim(m._product_description_hk)

from mochi.product p

	left join yahoo.vw_export_shipping_master m

		on p.upc_cd = m._product_upc_code

where p.dept_id = 1;



insert into mochi.product_shipping (prd_id, shp_dst_id, shp_typ_id, shp_wgt_lim, shp_wgt_frm, shp_wgt_to, shp_trk_lvl)

select prd_id,

	   sd.shp_dst_id,

	   st.shp_typ_id,

	   m._weight_limit, 

	   m._weight_from,

	   m._weight_to, 

	   m._tracking_level::smallint

from mochi.product p

	left join yahoo.vw_export_shipping_master m

		on p.upc_cd = m._product_upc_code

		

	left join mochi.shipping_destination sd

		on m._destination_code = sd.shp_dst_cd

		

	left join mochi.shipping_type st 

		on m._service_type_code = st.shp_typ_cd

where p.dept_id = 1;



	RETURN 1;

end;

';


ALTER FUNCTION mochi.load_shipping_products() OWNER TO mochidb_owner;

--
-- Name: accessories_attr_lcl_prd_lcl_id_seq; Type: SEQUENCE; Schema: mochi; Owner: postgres
--

CREATE SEQUENCE mochi.accessories_attr_lcl_prd_lcl_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mochi.accessories_attr_lcl_prd_lcl_id_seq OWNER TO postgres;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: accessories_attr_lcl; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE mochi.accessories_attr_lcl (
    prd_lcl_id bigint DEFAULT nextval('mochi.accessories_attr_lcl_prd_lcl_id_seq'::regclass) NOT NULL,
    prd_id bigint NOT NULL,
    primary_metal_type character varying(100),
    primary_stone_type character varying(100),
    lcl_cd character varying(5) NOT NULL
);


ALTER TABLE mochi.accessories_attr_lcl OWNER TO mochidb_owner;

--
-- Name: address_addr_id_seq; Type: SEQUENCE; Schema: mochi; Owner: postgres
--

CREATE SEQUENCE mochi.address_addr_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mochi.address_addr_id_seq OWNER TO postgres;

--
-- Name: address; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE mochi.address (
    addr_id bigint DEFAULT nextval('mochi.address_addr_id_seq'::regclass) NOT NULL,
    addr_ln_1 character varying(100),
    addr_ln_2 character varying(100),
    addr_ln_3 character varying(100),
    addr_cnty character varying(100) NOT NULL,
    addr_pst_cd character varying(10),
    addr_typ_id bigint NOT NULL,
    pty_id bigint
);


ALTER TABLE mochi.address OWNER TO mochidb_owner;

--
-- Name: address_type_addr_typ_id_seq; Type: SEQUENCE; Schema: mochi; Owner: postgres
--

CREATE SEQUENCE mochi.address_type_addr_typ_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mochi.address_type_addr_typ_id_seq OWNER TO postgres;

--
-- Name: address_type; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE mochi.address_type (
    addr_typ_id bigint DEFAULT nextval('mochi.address_type_addr_typ_id_seq'::regclass) NOT NULL,
    addr_typ_cd character varying(10) NOT NULL,
    addr_typ_desc character varying(20) NOT NULL
);


ALTER TABLE mochi.address_type OWNER TO mochidb_owner;

--
-- Name: bag_bag_id_seq; Type: SEQUENCE; Schema: mochi; Owner: mochidb_owner
--

CREATE SEQUENCE mochi.bag_bag_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mochi.bag_bag_id_seq OWNER TO mochidb_owner;

--
-- Name: bag; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE mochi.bag (
    bag_id bigint DEFAULT nextval('mochi.bag_bag_id_seq'::regclass) NOT NULL,
    pty_id bigint NOT NULL,
    bag_crd_dt timestamp with time zone NOT NULL,
    bag_upd_dt timestamp with time zone NOT NULL,
    prm_id bigint
);


ALTER TABLE mochi.bag OWNER TO mochidb_owner;

--
-- Name: bag_item_bag_item_id_seq; Type: SEQUENCE; Schema: mochi; Owner: postgres
--

CREATE SEQUENCE mochi.bag_item_bag_item_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mochi.bag_item_bag_item_id_seq OWNER TO postgres;

--
-- Name: bag_item; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE mochi.bag_item (
    bag_item_id bigint DEFAULT nextval('mochi.bag_item_bag_item_id_seq'::regclass) NOT NULL,
    bag_id bigint NOT NULL,
    prd_id bigint NOT NULL,
    qty smallint NOT NULL,
    bag_item_sts_id bigint NOT NULL
);


ALTER TABLE mochi.bag_item OWNER TO mochidb_owner;

--
-- Name: bag_item_disc_bag_item_disc_id_seq; Type: SEQUENCE; Schema: mochi; Owner: postgres
--

CREATE SEQUENCE mochi.bag_item_disc_bag_item_disc_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mochi.bag_item_disc_bag_item_disc_id_seq OWNER TO postgres;

--
-- Name: bag_item_disc; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE mochi.bag_item_disc (
    bag_item_disc_id bigint DEFAULT nextval('mochi.bag_item_disc_bag_item_disc_id_seq'::regclass) NOT NULL,
    bag_item_id bigint NOT NULL,
    disc_amt numeric NOT NULL
);


ALTER TABLE mochi.bag_item_disc OWNER TO mochidb_owner;

--
-- Name: bag_item_status_bag_item_sts_id_seq; Type: SEQUENCE; Schema: mochi; Owner: postgres
--

CREATE SEQUENCE mochi.bag_item_status_bag_item_sts_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mochi.bag_item_status_bag_item_sts_id_seq OWNER TO postgres;

--
-- Name: bag_item_status; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE mochi.bag_item_status (
    bag_item_sts_id bigint DEFAULT nextval('mochi.bag_item_status_bag_item_sts_id_seq'::regclass) NOT NULL,
    bag_item_sts_cd character varying(5) NOT NULL,
    bag_item_sts_desc character varying(20)
);


ALTER TABLE mochi.bag_item_status OWNER TO mochidb_owner;

--
-- Name: brand_bnd_id_seq; Type: SEQUENCE; Schema: mochi; Owner: mochidb_owner
--

CREATE SEQUENCE mochi.brand_bnd_id_seq
    START WITH 6
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mochi.brand_bnd_id_seq OWNER TO mochidb_owner;

--
-- Name: brand; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE mochi.brand (
    bnd_id bigint DEFAULT nextval('mochi.brand_bnd_id_seq'::regclass) NOT NULL,
    bnd_cd character(5)
);


ALTER TABLE mochi.brand OWNER TO mochidb_owner;

--
-- Name: brand_attr_lcl_bnd_lcl_id_seq; Type: SEQUENCE; Schema: mochi; Owner: postgres
--

CREATE SEQUENCE mochi.brand_attr_lcl_bnd_lcl_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mochi.brand_attr_lcl_bnd_lcl_id_seq OWNER TO postgres;

--
-- Name: brand_attr_lcl; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE mochi.brand_attr_lcl (
    bnd_lcl_id bigint DEFAULT nextval('mochi.brand_attr_lcl_bnd_lcl_id_seq'::regclass) NOT NULL,
    bnd_id bigint NOT NULL,
    bnd_desc character varying(100),
    bnd_img_pth character varying(100),
    lcl_cd character varying(5) NOT NULL
);


ALTER TABLE mochi.brand_attr_lcl OWNER TO mochidb_owner;

--
-- Name: brand_attr_lcl_bnd_id_seq; Type: SEQUENCE; Schema: mochi; Owner: mochidb_owner
--

CREATE SEQUENCE mochi.brand_attr_lcl_bnd_id_seq
    START WITH 19
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mochi.brand_attr_lcl_bnd_id_seq OWNER TO mochidb_owner;

--
-- Name: brand_category_bnd_cat_id_seq; Type: SEQUENCE; Schema: mochi; Owner: mochidb_owner
--

CREATE SEQUENCE mochi.brand_category_bnd_cat_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mochi.brand_category_bnd_cat_id_seq OWNER TO mochidb_owner;

--
-- Name: brand_category; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE mochi.brand_category (
    bnd_cat_id bigint DEFAULT nextval('mochi.brand_category_bnd_cat_id_seq'::regclass) NOT NULL,
    bnd_id bigint,
    cat_id bigint
);


ALTER TABLE mochi.brand_category OWNER TO mochidb_owner;

--
-- Name: brand_promotion_bnd_id_seq; Type: SEQUENCE; Schema: mochi; Owner: postgres
--

CREATE SEQUENCE mochi.brand_promotion_bnd_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mochi.brand_promotion_bnd_id_seq OWNER TO postgres;

--
-- Name: brand_promotion_prm_id_seq; Type: SEQUENCE; Schema: mochi; Owner: postgres
--

CREATE SEQUENCE mochi.brand_promotion_prm_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mochi.brand_promotion_prm_id_seq OWNER TO postgres;

--
-- Name: brand_promotion; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE mochi.brand_promotion (
    bnd_id bigint DEFAULT nextval('mochi.brand_promotion_bnd_id_seq'::regclass) NOT NULL,
    prm_id bigint DEFAULT nextval('mochi.brand_promotion_prm_id_seq'::regclass) NOT NULL
);


ALTER TABLE mochi.brand_promotion OWNER TO mochidb_owner;

--
-- Name: category_cat_id_seq; Type: SEQUENCE; Schema: mochi; Owner: mochidb_owner
--

CREATE SEQUENCE mochi.category_cat_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mochi.category_cat_id_seq OWNER TO mochidb_owner;

--
-- Name: category; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE mochi.category (
    cat_id bigint DEFAULT nextval('mochi.category_cat_id_seq'::regclass) NOT NULL,
    cat_cd character(5) NOT NULL,
    cat_typ_id bigint,
    cat_lvl bigint,
    cat_prnt_cd character(5),
    cat_prnt_id bigint
);


ALTER TABLE mochi.category OWNER TO mochidb_owner;

--
-- Name: category_attr_lcl_cat_lcl_id_seq; Type: SEQUENCE; Schema: mochi; Owner: postgres
--

CREATE SEQUENCE mochi.category_attr_lcl_cat_lcl_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mochi.category_attr_lcl_cat_lcl_id_seq OWNER TO postgres;

--
-- Name: category_attr_lcl; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE mochi.category_attr_lcl (
    cat_lcl_id bigint DEFAULT nextval('mochi.category_attr_lcl_cat_lcl_id_seq'::regclass) NOT NULL,
    cat_id bigint NOT NULL,
    cat_desc character varying(100),
    cat_img_pth character varying(100),
    lcl_cd character varying(5) NOT NULL
);


ALTER TABLE mochi.category_attr_lcl OWNER TO mochidb_owner;

--
-- Name: category_brand_cat_id_seq; Type: SEQUENCE; Schema: mochi; Owner: postgres
--

CREATE SEQUENCE mochi.category_brand_cat_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mochi.category_brand_cat_id_seq OWNER TO postgres;

--
-- Name: category_brand; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE mochi.category_brand (
    cat_id bigint DEFAULT nextval('mochi.category_brand_cat_id_seq'::regclass) NOT NULL
);


ALTER TABLE mochi.category_brand OWNER TO mochidb_owner;

--
-- Name: category_product_cat_id_seq; Type: SEQUENCE; Schema: mochi; Owner: postgres
--

CREATE SEQUENCE mochi.category_product_cat_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mochi.category_product_cat_id_seq OWNER TO postgres;

--
-- Name: category_product; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE mochi.category_product (
    cat_id bigint DEFAULT nextval('mochi.category_product_cat_id_seq'::regclass) NOT NULL
);


ALTER TABLE mochi.category_product OWNER TO mochidb_owner;

--
-- Name: category_promotion_cat_id_seq; Type: SEQUENCE; Schema: mochi; Owner: postgres
--

CREATE SEQUENCE mochi.category_promotion_cat_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mochi.category_promotion_cat_id_seq OWNER TO postgres;

--
-- Name: category_promotion_prm_id_seq; Type: SEQUENCE; Schema: mochi; Owner: postgres
--

CREATE SEQUENCE mochi.category_promotion_prm_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mochi.category_promotion_prm_id_seq OWNER TO postgres;

--
-- Name: category_promotion; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE mochi.category_promotion (
    cat_id bigint DEFAULT nextval('mochi.category_promotion_cat_id_seq'::regclass) NOT NULL,
    prm_id bigint DEFAULT nextval('mochi.category_promotion_prm_id_seq'::regclass) NOT NULL
);


ALTER TABLE mochi.category_promotion OWNER TO mochidb_owner;

--
-- Name: category_type_cat_typ_id_seq; Type: SEQUENCE; Schema: mochi; Owner: mochidb_owner
--

CREATE SEQUENCE mochi.category_type_cat_typ_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mochi.category_type_cat_typ_id_seq OWNER TO mochidb_owner;

--
-- Name: category_type; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE mochi.category_type (
    cat_typ_id bigint DEFAULT nextval('mochi.category_type_cat_typ_id_seq'::regclass) NOT NULL,
    cat_typ_cd character varying(5) NOT NULL,
    cat_typ_desc character varying(20)
);


ALTER TABLE mochi.category_type OWNER TO mochidb_owner;

--
-- Name: currency_ccy_id_seq; Type: SEQUENCE; Schema: mochi; Owner: postgres
--

CREATE SEQUENCE mochi.currency_ccy_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mochi.currency_ccy_id_seq OWNER TO postgres;

--
-- Name: currency; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE mochi.currency (
    ccy_id bigint DEFAULT nextval('mochi.currency_ccy_id_seq'::regclass) NOT NULL,
    ccy_cd character(3)
);


ALTER TABLE mochi.currency OWNER TO mochidb_owner;

--
-- Name: customer_cst_num_seq; Type: SEQUENCE; Schema: mochi; Owner: mochidb_owner
--

CREATE SEQUENCE mochi.customer_cst_num_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mochi.customer_cst_num_seq OWNER TO mochidb_owner;

--
-- Name: customer_rle_id_seq; Type: SEQUENCE; Schema: mochi; Owner: postgres
--

CREATE SEQUENCE mochi.customer_rle_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mochi.customer_rle_id_seq OWNER TO postgres;

--
-- Name: customer; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE mochi.customer (
    rle_id bigint DEFAULT nextval('mochi.customer_rle_id_seq'::regclass) NOT NULL,
    cst_num character(10) DEFAULT nextval('mochi.customer_cst_num_seq'::regclass) NOT NULL
);


ALTER TABLE mochi.customer OWNER TO mochidb_owner;

--
-- Name: department_dept_id_seq; Type: SEQUENCE; Schema: mochi; Owner: postgres
--

CREATE SEQUENCE mochi.department_dept_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mochi.department_dept_id_seq OWNER TO postgres;

--
-- Name: department; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE mochi.department (
    dept_id bigint DEFAULT nextval('mochi.department_dept_id_seq'::regclass) NOT NULL,
    dept_cd character varying(5) NOT NULL
);


ALTER TABLE mochi.department OWNER TO mochidb_owner;

--
-- Name: department_attr_lcl_dept_lcl_id_seq; Type: SEQUENCE; Schema: mochi; Owner: postgres
--

CREATE SEQUENCE mochi.department_attr_lcl_dept_lcl_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mochi.department_attr_lcl_dept_lcl_id_seq OWNER TO postgres;

--
-- Name: department_attr_lcl; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE mochi.department_attr_lcl (
    dept_lcl_id bigint DEFAULT nextval('mochi.department_attr_lcl_dept_lcl_id_seq'::regclass) NOT NULL,
    dept_id bigint NOT NULL,
    dept_desc character varying(100),
    lcl_cd character varying(5) NOT NULL
);


ALTER TABLE mochi.department_attr_lcl OWNER TO mochidb_owner;

--
-- Name: discount_dis_id_seq; Type: SEQUENCE; Schema: mochi; Owner: postgres
--

CREATE SEQUENCE mochi.discount_dis_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mochi.discount_dis_id_seq OWNER TO postgres;

--
-- Name: discount; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE mochi.discount (
    dis_id bigint DEFAULT nextval('mochi.discount_dis_id_seq'::regclass) NOT NULL,
    dis_cd character(5),
    prm_id bigint,
    dis_typ_id bigint NOT NULL,
    dis_val numeric
);


ALTER TABLE mochi.discount OWNER TO mochidb_owner;

--
-- Name: discount_type_dis_typ_id_seq; Type: SEQUENCE; Schema: mochi; Owner: postgres
--

CREATE SEQUENCE mochi.discount_type_dis_typ_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mochi.discount_type_dis_typ_id_seq OWNER TO postgres;

--
-- Name: discount_type; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE mochi.discount_type (
    dis_typ_id bigint DEFAULT nextval('mochi.discount_type_dis_typ_id_seq'::regclass) NOT NULL,
    dis_typ_desc character varying NOT NULL
);


ALTER TABLE mochi.discount_type OWNER TO mochidb_owner;

--
-- Name: inventory_location_inv_loc_id_seq; Type: SEQUENCE; Schema: mochi; Owner: postgres
--

CREATE SEQUENCE mochi.inventory_location_inv_loc_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mochi.inventory_location_inv_loc_id_seq OWNER TO postgres;

--
-- Name: inventory_location; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE mochi.inventory_location (
    inv_loc_id bigint DEFAULT nextval('mochi.inventory_location_inv_loc_id_seq'::regclass) NOT NULL,
    inv_loc_cd character(5) NOT NULL,
    inv_loc_desc character varying(50) NOT NULL,
    inv_loc_act boolean NOT NULL
);


ALTER TABLE mochi.inventory_location OWNER TO mochidb_owner;

--
-- Name: inventory_transaction_inv_trx_id_seq; Type: SEQUENCE; Schema: mochi; Owner: mochidb_owner
--

CREATE SEQUENCE mochi.inventory_transaction_inv_trx_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mochi.inventory_transaction_inv_trx_id_seq OWNER TO mochidb_owner;

--
-- Name: inventory_transaction; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE mochi.inventory_transaction (
    inv_trx_id bigint DEFAULT nextval('mochi.inventory_transaction_inv_trx_id_seq'::regclass) NOT NULL,
    inv_loc_id bigint NOT NULL,
    inv_prd_id bigint NOT NULL,
    inv_qty bigint NOT NULL,
    inv_prc numeric NOT NULL,
    inv_ccy_id bigint NOT NULL,
    inv_trx_typ_id bigint NOT NULL,
    inv_pty_id bigint NOT NULL,
    inv_trx_dt timestamp(4) with time zone NOT NULL
);


ALTER TABLE mochi.inventory_transaction OWNER TO mochidb_owner;

--
-- Name: inventory_transaction_type_inv_trx_typ_id_seq; Type: SEQUENCE; Schema: mochi; Owner: postgres
--

CREATE SEQUENCE mochi.inventory_transaction_type_inv_trx_typ_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mochi.inventory_transaction_type_inv_trx_typ_id_seq OWNER TO postgres;

--
-- Name: inventory_transaction_type; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE mochi.inventory_transaction_type (
    inv_trx_typ_id bigint DEFAULT nextval('mochi.inventory_transaction_type_inv_trx_typ_id_seq'::regclass) NOT NULL,
    inv_trx_typ_cd character varying(10) NOT NULL
);


ALTER TABLE mochi.inventory_transaction_type OWNER TO mochidb_owner;

--
-- Name: locale_lcl_cd_seq; Type: SEQUENCE; Schema: mochi; Owner: postgres
--

CREATE SEQUENCE mochi.locale_lcl_cd_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mochi.locale_lcl_cd_seq OWNER TO postgres;

--
-- Name: locale; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE mochi.locale (
    lcl_cd character varying(20) DEFAULT nextval('mochi.locale_lcl_cd_seq'::regclass) NOT NULL
);


ALTER TABLE mochi.locale OWNER TO mochidb_owner;

--
-- Name: order_ord_id_seq; Type: SEQUENCE; Schema: mochi; Owner: postgres
--

CREATE SEQUENCE mochi.order_ord_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mochi.order_ord_id_seq OWNER TO postgres;

--
-- Name: order; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE mochi."order" (
    ord_id bigint DEFAULT nextval('mochi.order_ord_id_seq'::regclass) NOT NULL,
    pty_id bigint NOT NULL
);


ALTER TABLE mochi."order" OWNER TO mochidb_owner;

--
-- Name: order_line_ord_id_seq; Type: SEQUENCE; Schema: mochi; Owner: postgres
--

CREATE SEQUENCE mochi.order_line_ord_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mochi.order_line_ord_id_seq OWNER TO postgres;

--
-- Name: order_line_ord_lne_no_seq; Type: SEQUENCE; Schema: mochi; Owner: postgres
--

CREATE SEQUENCE mochi.order_line_ord_lne_no_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mochi.order_line_ord_lne_no_seq OWNER TO postgres;

--
-- Name: order_line_prd_id_seq; Type: SEQUENCE; Schema: mochi; Owner: postgres
--

CREATE SEQUENCE mochi.order_line_prd_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mochi.order_line_prd_id_seq OWNER TO postgres;

--
-- Name: order_line; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE mochi.order_line (
    ord_id bigint DEFAULT nextval('mochi.order_line_ord_id_seq'::regclass) NOT NULL,
    prd_id bigint DEFAULT nextval('mochi.order_line_prd_id_seq'::regclass) NOT NULL,
    ord_lne_no bigint DEFAULT nextval('mochi.order_line_ord_lne_no_seq'::regclass) NOT NULL,
    qty smallint NOT NULL,
    prm_id bigint NOT NULL,
    amt money NOT NULL
);


ALTER TABLE mochi.order_line OWNER TO mochidb_owner;

--
-- Name: organisation; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE mochi.organisation (
    pty_id bigint NOT NULL,
    org_nme character varying(100) NOT NULL,
    org_reg_no character varying(50) NOT NULL
);


ALTER TABLE mochi.organisation OWNER TO mochidb_owner;

--
-- Name: party; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE mochi.party (
    pty_id bigint NOT NULL,
    pty_typ_id bigint NOT NULL
);


ALTER TABLE mochi.party OWNER TO mochidb_owner;

--
-- Name: party_pty_id_seq; Type: SEQUENCE; Schema: mochi; Owner: mochidb_owner
--

CREATE SEQUENCE mochi.party_pty_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mochi.party_pty_id_seq OWNER TO mochidb_owner;

--
-- Name: party_pty_id_seq; Type: SEQUENCE OWNED BY; Schema: mochi; Owner: mochidb_owner
--

ALTER SEQUENCE mochi.party_pty_id_seq OWNED BY mochi.party.pty_id;


--
-- Name: party_type; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE mochi.party_type (
    pty_typ_id bigint NOT NULL,
    pty_typ_desc character varying NOT NULL
);


ALTER TABLE mochi.party_type OWNER TO mochidb_owner;

--
-- Name: party_type_pty_typ_id_seq; Type: SEQUENCE; Schema: mochi; Owner: mochidb_owner
--

CREATE SEQUENCE mochi.party_type_pty_typ_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mochi.party_type_pty_typ_id_seq OWNER TO mochidb_owner;

--
-- Name: party_type_pty_typ_id_seq; Type: SEQUENCE OWNED BY; Schema: mochi; Owner: mochidb_owner
--

ALTER SEQUENCE mochi.party_type_pty_typ_id_seq OWNED BY mochi.party_type.pty_typ_id;


--
-- Name: person; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE mochi.person (
    pty_id bigint NOT NULL,
    psn_gvn_nm character varying NOT NULL,
    psn_fml_nm character varying NOT NULL,
    enb boolean NOT NULL
);


ALTER TABLE mochi.person OWNER TO mochidb_owner;

--
-- Name: price_prc_id_seq; Type: SEQUENCE; Schema: mochi; Owner: mochidb_owner
--

CREATE SEQUENCE mochi.price_prc_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mochi.price_prc_id_seq OWNER TO mochidb_owner;

--
-- Name: price; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE mochi.price (
    prc_id bigint DEFAULT nextval('mochi.price_prc_id_seq'::regclass) NOT NULL,
    prc_typ_id bigint NOT NULL,
    prd_id bigint NOT NULL,
    prc_val numeric NOT NULL,
    ccy_id bigint NOT NULL
);


ALTER TABLE mochi.price OWNER TO mochidb_owner;

--
-- Name: price_type_prc_typ_id_seq; Type: SEQUENCE; Schema: mochi; Owner: postgres
--

CREATE SEQUENCE mochi.price_type_prc_typ_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mochi.price_type_prc_typ_id_seq OWNER TO postgres;

--
-- Name: price_type; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE mochi.price_type (
    prc_typ_id bigint DEFAULT nextval('mochi.price_type_prc_typ_id_seq'::regclass) NOT NULL,
    prc_typ_desc character varying,
    prc_typ_cd character varying
);


ALTER TABLE mochi.price_type OWNER TO mochidb_owner;

--
-- Name: product_prd_id_seq; Type: SEQUENCE; Schema: mochi; Owner: mochidb_owner
--

CREATE SEQUENCE mochi.product_prd_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mochi.product_prd_id_seq OWNER TO mochidb_owner;

--
-- Name: product; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE mochi.product (
    prd_id bigint DEFAULT nextval('mochi.product_prd_id_seq'::regclass) NOT NULL,
    upc_cd character varying(30) NOT NULL,
    prd_crtd_dt timestamp(4) with time zone NOT NULL,
    bnd_id bigint NOT NULL,
    dept_id bigint NOT NULL,
    prd_sts_id bigint NOT NULL
);


ALTER TABLE mochi.product OWNER TO mochidb_owner;

--
-- Name: product_attr_lcl_prd_lcl_id_seq; Type: SEQUENCE; Schema: mochi; Owner: mochidb_owner
--

CREATE SEQUENCE mochi.product_attr_lcl_prd_lcl_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mochi.product_attr_lcl_prd_lcl_id_seq OWNER TO mochidb_owner;

--
-- Name: product_attr_lcl; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE mochi.product_attr_lcl (
    prd_lcl_id bigint DEFAULT nextval('mochi.product_attr_lcl_prd_lcl_id_seq'::regclass) NOT NULL,
    prd_id bigint NOT NULL,
    prd_desc character varying(200),
    prd_img_pth character varying(100),
    lcl_cd character varying(5) NOT NULL,
    prd_lng_desc text
);


ALTER TABLE mochi.product_attr_lcl OWNER TO mochidb_owner;

--
-- Name: product_basic_prd_id_seq; Type: SEQUENCE; Schema: mochi; Owner: postgres
--

CREATE SEQUENCE mochi.product_basic_prd_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mochi.product_basic_prd_id_seq OWNER TO postgres;

--
-- Name: product_basic; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE mochi.product_basic (
    prd_id bigint DEFAULT nextval('mochi.product_basic_prd_id_seq'::regclass) NOT NULL,
    width bigint,
    height bigint,
    length bigint,
    weight numeric(8,2)
);


ALTER TABLE mochi.product_basic OWNER TO mochidb_owner;

--
-- Name: product_category_prd_cat_id_seq; Type: SEQUENCE; Schema: mochi; Owner: mochidb_owner
--

CREATE SEQUENCE mochi.product_category_prd_cat_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mochi.product_category_prd_cat_id_seq OWNER TO mochidb_owner;

--
-- Name: product_category; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE mochi.product_category (
    prd_cat_id bigint DEFAULT nextval('mochi.product_category_prd_cat_id_seq'::regclass) NOT NULL,
    prd_id bigint,
    cat_id bigint
);


ALTER TABLE mochi.product_category OWNER TO mochidb_owner;

--
-- Name: product_promotion_prd_id_seq; Type: SEQUENCE; Schema: mochi; Owner: postgres
--

CREATE SEQUENCE mochi.product_promotion_prd_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mochi.product_promotion_prd_id_seq OWNER TO postgres;

--
-- Name: product_promotion_prm_id_seq; Type: SEQUENCE; Schema: mochi; Owner: postgres
--

CREATE SEQUENCE mochi.product_promotion_prm_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mochi.product_promotion_prm_id_seq OWNER TO postgres;

--
-- Name: product_promotion; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE mochi.product_promotion (
    prd_id bigint DEFAULT nextval('mochi.product_promotion_prd_id_seq'::regclass) NOT NULL,
    prm_id bigint DEFAULT nextval('mochi.product_promotion_prm_id_seq'::regclass) NOT NULL
);


ALTER TABLE mochi.product_promotion OWNER TO mochidb_owner;

--
-- Name: product_rating_prd_rat_id_seq; Type: SEQUENCE; Schema: mochi; Owner: mochidb_owner
--

CREATE SEQUENCE mochi.product_rating_prd_rat_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mochi.product_rating_prd_rat_id_seq OWNER TO mochidb_owner;

--
-- Name: product_rating; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE mochi.product_rating (
    prd_rat_id bigint DEFAULT nextval('mochi.product_rating_prd_rat_id_seq'::regclass) NOT NULL,
    prd_id bigint NOT NULL,
    rat_id bigint NOT NULL,
    pty_id bigint NOT NULL,
    prd_rat_dt timestamp with time zone NOT NULL
);


ALTER TABLE mochi.product_rating OWNER TO mochidb_owner;

--
-- Name: product_shipping_prd_id_seq; Type: SEQUENCE; Schema: mochi; Owner: postgres
--

CREATE SEQUENCE mochi.product_shipping_prd_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mochi.product_shipping_prd_id_seq OWNER TO postgres;

--
-- Name: product_shipping; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE mochi.product_shipping (
    prd_id bigint DEFAULT nextval('mochi.product_shipping_prd_id_seq'::regclass) NOT NULL,
    shp_wgt_lim numeric,
    shp_wgt_frm numeric NOT NULL,
    shp_wgt_to numeric NOT NULL,
    shp_typ_cd character(6) NOT NULL,
    shp_ctry_cd character(3) NOT NULL
);


ALTER TABLE mochi.product_shipping OWNER TO mochidb_owner;

--
-- Name: product_shipping_attr_lcl_prd_lcl_id_seq; Type: SEQUENCE; Schema: mochi; Owner: mochidb_owner
--

CREATE SEQUENCE mochi.product_shipping_attr_lcl_prd_lcl_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mochi.product_shipping_attr_lcl_prd_lcl_id_seq OWNER TO mochidb_owner;

--
-- Name: product_shipping_attr_lcl; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE mochi.product_shipping_attr_lcl (
    prd_lcl_id bigint DEFAULT nextval('mochi.product_shipping_attr_lcl_prd_lcl_id_seq'::regclass) NOT NULL,
    prd_id bigint NOT NULL,
    prd_shp_typ_desc character varying(100),
    prd_shp_ctry_desc character varying(100),
    lcl_cd character varying(5) NOT NULL
);


ALTER TABLE mochi.product_shipping_attr_lcl OWNER TO mochidb_owner;

--
-- Name: product_status_prd_sts_id_seq; Type: SEQUENCE; Schema: mochi; Owner: mochidb_owner
--

CREATE SEQUENCE mochi.product_status_prd_sts_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mochi.product_status_prd_sts_id_seq OWNER TO mochidb_owner;

--
-- Name: product_status; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE mochi.product_status (
    prd_sts_id bigint DEFAULT nextval('mochi.product_status_prd_sts_id_seq'::regclass) NOT NULL,
    prd_sts_cd character varying(5) NOT NULL,
    prd_sts_desc character varying(20)
);


ALTER TABLE mochi.product_status OWNER TO mochidb_owner;

--
-- Name: product_supplier; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE mochi.product_supplier (
);


ALTER TABLE mochi.product_supplier OWNER TO mochidb_owner;

--
-- Name: product_tag_prd_tag_id_seq; Type: SEQUENCE; Schema: mochi; Owner: mochidb_owner
--

CREATE SEQUENCE mochi.product_tag_prd_tag_id_seq
    START WITH 25
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mochi.product_tag_prd_tag_id_seq OWNER TO mochidb_owner;

--
-- Name: product_tag; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE mochi.product_tag (
    prd_tag_id bigint DEFAULT nextval('mochi.product_tag_prd_tag_id_seq'::regclass) NOT NULL,
    prd_id bigint,
    tag_id bigint
);


ALTER TABLE mochi.product_tag OWNER TO mochidb_owner;

--
-- Name: promotion_prm_id_seq; Type: SEQUENCE; Schema: mochi; Owner: postgres
--

CREATE SEQUENCE mochi.promotion_prm_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mochi.promotion_prm_id_seq OWNER TO postgres;

--
-- Name: promotion; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE mochi.promotion (
    prm_id bigint DEFAULT nextval('mochi.promotion_prm_id_seq'::regclass) NOT NULL,
    prm_cd character(6) NOT NULL,
    prm_st_dt timestamp(4) with time zone NOT NULL,
    prm_en_dt timestamp(4) with time zone NOT NULL,
    prm_mec_id bigint NOT NULL,
    prm_act boolean NOT NULL,
    prm_typ_id bigint NOT NULL,
    prm_lvl_id bigint NOT NULL,
    prm_dis_id bigint
);


ALTER TABLE mochi.promotion OWNER TO mochidb_owner;

--
-- Name: promotion_attr_lcl_prm_lcl_id_seq; Type: SEQUENCE; Schema: mochi; Owner: postgres
--

CREATE SEQUENCE mochi.promotion_attr_lcl_prm_lcl_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mochi.promotion_attr_lcl_prm_lcl_id_seq OWNER TO postgres;

--
-- Name: promotion_attr_lcl; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE mochi.promotion_attr_lcl (
    prm_lcl_id bigint DEFAULT nextval('mochi.promotion_attr_lcl_prm_lcl_id_seq'::regclass) NOT NULL,
    prm_id bigint NOT NULL,
    prm_desc character varying(100),
    lcl_cd character varying(5) NOT NULL
);


ALTER TABLE mochi.promotion_attr_lcl OWNER TO mochidb_owner;

--
-- Name: promotion_level_prm_lvl_id_seq; Type: SEQUENCE; Schema: mochi; Owner: postgres
--

CREATE SEQUENCE mochi.promotion_level_prm_lvl_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mochi.promotion_level_prm_lvl_id_seq OWNER TO postgres;

--
-- Name: promotion_level; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE mochi.promotion_level (
    prm_lvl_id bigint DEFAULT nextval('mochi.promotion_level_prm_lvl_id_seq'::regclass) NOT NULL,
    prm_lvl_cd character varying(5) NOT NULL,
    prm_lvl_desc character varying(50) NOT NULL
);


ALTER TABLE mochi.promotion_level OWNER TO mochidb_owner;

--
-- Name: promotion_mechanic_prm_mec_id_seq; Type: SEQUENCE; Schema: mochi; Owner: postgres
--

CREATE SEQUENCE mochi.promotion_mechanic_prm_mec_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mochi.promotion_mechanic_prm_mec_id_seq OWNER TO postgres;

--
-- Name: promotion_mechanic; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE mochi.promotion_mechanic (
    prm_mec_id bigint DEFAULT nextval('mochi.promotion_mechanic_prm_mec_id_seq'::regclass) NOT NULL,
    prm_mec_cd character varying(10) NOT NULL,
    prm_mec_desc character varying
);


ALTER TABLE mochi.promotion_mechanic OWNER TO mochidb_owner;

--
-- Name: promotion_order_prm_id_seq; Type: SEQUENCE; Schema: mochi; Owner: postgres
--

CREATE SEQUENCE mochi.promotion_order_prm_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mochi.promotion_order_prm_id_seq OWNER TO postgres;

--
-- Name: promotion_order; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE mochi.promotion_order (
    prm_id bigint DEFAULT nextval('mochi.promotion_order_prm_id_seq'::regclass) NOT NULL,
    prm_cpn_cd character varying(15) NOT NULL
);


ALTER TABLE mochi.promotion_order OWNER TO mochidb_owner;

--
-- Name: promotion_product_prm_id_seq; Type: SEQUENCE; Schema: mochi; Owner: postgres
--

CREATE SEQUENCE mochi.promotion_product_prm_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mochi.promotion_product_prm_id_seq OWNER TO postgres;

--
-- Name: promotion_product; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE mochi.promotion_product (
    prm_id bigint DEFAULT nextval('mochi.promotion_product_prm_id_seq'::regclass) NOT NULL
);


ALTER TABLE mochi.promotion_product OWNER TO mochidb_owner;

--
-- Name: promotion_type_prm_typ_id_seq; Type: SEQUENCE; Schema: mochi; Owner: postgres
--

CREATE SEQUENCE mochi.promotion_type_prm_typ_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mochi.promotion_type_prm_typ_id_seq OWNER TO postgres;

--
-- Name: promotion_type; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE mochi.promotion_type (
    prm_typ_id bigint DEFAULT nextval('mochi.promotion_type_prm_typ_id_seq'::regclass) NOT NULL,
    prm_typ_cd character varying(5) NOT NULL,
    prm_class character varying(20) NOT NULL,
    prm_typ_desc character varying(50) NOT NULL
);


ALTER TABLE mochi.promotion_type OWNER TO mochidb_owner;

--
-- Name: rating_rat_id_seq; Type: SEQUENCE; Schema: mochi; Owner: postgres
--

CREATE SEQUENCE mochi.rating_rat_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mochi.rating_rat_id_seq OWNER TO postgres;

--
-- Name: rating; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE mochi.rating (
    rat_id bigint DEFAULT nextval('mochi.rating_rat_id_seq'::regclass) NOT NULL,
    rat_desc character varying(50),
    rat_val smallint
);


ALTER TABLE mochi.rating OWNER TO mochidb_owner;

--
-- Name: role_rle_id_seq; Type: SEQUENCE; Schema: mochi; Owner: mochidb_owner
--

CREATE SEQUENCE mochi.role_rle_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mochi.role_rle_id_seq OWNER TO mochidb_owner;

--
-- Name: role; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE mochi.role (
    rle_id bigint DEFAULT nextval('mochi.role_rle_id_seq'::regclass) NOT NULL,
    rle_typ_id bigint NOT NULL,
    rle_start_dt timestamp(4) with time zone DEFAULT now() NOT NULL,
    pty_id bigint NOT NULL
);


ALTER TABLE mochi.role OWNER TO mochidb_owner;

--
-- Name: role_type; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE mochi.role_type (
    rle_typ_id bigint NOT NULL,
    rle_typ_desc character varying NOT NULL
);


ALTER TABLE mochi.role_type OWNER TO mochidb_owner;

--
-- Name: role_type_rle_typ_id_seq; Type: SEQUENCE; Schema: mochi; Owner: mochidb_owner
--

CREATE SEQUENCE mochi.role_type_rle_typ_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mochi.role_type_rle_typ_id_seq OWNER TO mochidb_owner;

--
-- Name: role_type_rle_typ_id_seq; Type: SEQUENCE OWNED BY; Schema: mochi; Owner: mochidb_owner
--

ALTER SEQUENCE mochi.role_type_rle_typ_id_seq OWNED BY mochi.role_type.rle_typ_id;


--
-- Name: stock_on_hand_soh_id_seq; Type: SEQUENCE; Schema: mochi; Owner: postgres
--

CREATE SEQUENCE mochi.stock_on_hand_soh_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mochi.stock_on_hand_soh_id_seq OWNER TO postgres;

--
-- Name: stock_on_hand; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE mochi.stock_on_hand (
    soh_id bigint DEFAULT nextval('mochi.stock_on_hand_soh_id_seq'::regclass) NOT NULL,
    soh_prd_id bigint NOT NULL,
    soh_qty bigint NOT NULL
);


ALTER TABLE mochi.stock_on_hand OWNER TO mochidb_owner;

--
-- Name: supplier_rle_id_seq; Type: SEQUENCE; Schema: mochi; Owner: postgres
--

CREATE SEQUENCE mochi.supplier_rle_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mochi.supplier_rle_id_seq OWNER TO postgres;

--
-- Name: supplier_sup_num_seq; Type: SEQUENCE; Schema: mochi; Owner: mochidb_owner
--

CREATE SEQUENCE mochi.supplier_sup_num_seq
    START WITH 1000000001
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mochi.supplier_sup_num_seq OWNER TO mochidb_owner;

--
-- Name: supplier; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE mochi.supplier (
    rle_id bigint DEFAULT nextval('mochi.supplier_rle_id_seq'::regclass) NOT NULL,
    sup_num character(10) DEFAULT nextval('mochi.supplier_sup_num_seq'::regclass) NOT NULL
);


ALTER TABLE mochi.supplier OWNER TO mochidb_owner;

--
-- Name: tag_tag_id_seq; Type: SEQUENCE; Schema: mochi; Owner: mochidb_owner
--

CREATE SEQUENCE mochi.tag_tag_id_seq
    START WITH 15
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mochi.tag_tag_id_seq OWNER TO mochidb_owner;

--
-- Name: tag; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE mochi.tag (
    tag_id bigint DEFAULT nextval('mochi.tag_tag_id_seq'::regclass) NOT NULL,
    tag_cd character(5) NOT NULL
);


ALTER TABLE mochi.tag OWNER TO mochidb_owner;

--
-- Name: tag_attr_lcl_tag_lcl_id_seq; Type: SEQUENCE; Schema: mochi; Owner: postgres
--

CREATE SEQUENCE mochi.tag_attr_lcl_tag_lcl_id_seq
    START WITH 6
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mochi.tag_attr_lcl_tag_lcl_id_seq OWNER TO postgres;

--
-- Name: tag_attr_lcl; Type: TABLE; Schema: mochi; Owner: mochidb_owner
--

CREATE TABLE mochi.tag_attr_lcl (
    tag_lcl_id bigint DEFAULT nextval('mochi.tag_attr_lcl_tag_lcl_id_seq'::regclass) NOT NULL,
    tag_id bigint NOT NULL,
    tag_desc character varying(100),
    tag_img_pth character varying(100),
    lcl_cd character varying(5) NOT NULL
);


ALTER TABLE mochi.tag_attr_lcl OWNER TO mochidb_owner;

--
-- Name: party pty_id; Type: DEFAULT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.party ALTER COLUMN pty_id SET DEFAULT nextval('mochi.party_pty_id_seq'::regclass);


--
-- Name: party_type pty_typ_id; Type: DEFAULT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.party_type ALTER COLUMN pty_typ_id SET DEFAULT nextval('mochi.party_type_pty_typ_id_seq'::regclass);


--
-- Name: role_type rle_typ_id; Type: DEFAULT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.role_type ALTER COLUMN rle_typ_id SET DEFAULT nextval('mochi.role_type_rle_typ_id_seq'::regclass);


--
-- Name: accessories_attr_lcl accessories_attr_lcl_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.accessories_attr_lcl
    ADD CONSTRAINT accessories_attr_lcl_pkey PRIMARY KEY (prd_lcl_id);


--
-- Name: address address_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.address
    ADD CONSTRAINT address_pkey PRIMARY KEY (addr_id);


--
-- Name: address_type address_type_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.address_type
    ADD CONSTRAINT address_type_pkey PRIMARY KEY (addr_typ_id);


--
-- Name: bag_item_disc bag_item_disc_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.bag_item_disc
    ADD CONSTRAINT bag_item_disc_pkey PRIMARY KEY (bag_item_disc_id);


--
-- Name: bag_item bag_item_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.bag_item
    ADD CONSTRAINT bag_item_pkey PRIMARY KEY (bag_item_id);


--
-- Name: bag bag_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.bag
    ADD CONSTRAINT bag_pkey PRIMARY KEY (bag_id);


--
-- Name: bag_item_status bag_status_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.bag_item_status
    ADD CONSTRAINT bag_status_pkey PRIMARY KEY (bag_item_sts_id);


--
-- Name: brand_attr_lcl brand_attr_lcl_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.brand_attr_lcl
    ADD CONSTRAINT brand_attr_lcl_pkey PRIMARY KEY (bnd_lcl_id);


--
-- Name: brand_category brand_category_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.brand_category
    ADD CONSTRAINT brand_category_pkey PRIMARY KEY (bnd_cat_id);


--
-- Name: brand brand_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.brand
    ADD CONSTRAINT brand_pkey PRIMARY KEY (bnd_id);


--
-- Name: brand_promotion brand_promotion_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.brand_promotion
    ADD CONSTRAINT brand_promotion_pkey PRIMARY KEY (bnd_id, prm_id);


--
-- Name: category_attr_lcl category_attr_lcl_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.category_attr_lcl
    ADD CONSTRAINT category_attr_lcl_pkey PRIMARY KEY (cat_lcl_id);


--
-- Name: category_brand category_brand_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.category_brand
    ADD CONSTRAINT category_brand_pkey PRIMARY KEY (cat_id);


--
-- Name: category category_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.category
    ADD CONSTRAINT category_pkey PRIMARY KEY (cat_id);


--
-- Name: category_product category_product_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.category_product
    ADD CONSTRAINT category_product_pkey PRIMARY KEY (cat_id);


--
-- Name: category_promotion category_promotion_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.category_promotion
    ADD CONSTRAINT category_promotion_pkey PRIMARY KEY (cat_id, prm_id);


--
-- Name: category_type category_type_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.category_type
    ADD CONSTRAINT category_type_pkey PRIMARY KEY (cat_typ_id);


--
-- Name: currency currency_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.currency
    ADD CONSTRAINT currency_pkey PRIMARY KEY (ccy_id);


--
-- Name: customer customer_cst_id_key; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.customer
    ADD CONSTRAINT customer_cst_id_key UNIQUE (cst_num);


--
-- Name: customer customer_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.customer
    ADD CONSTRAINT customer_pkey PRIMARY KEY (rle_id);


--
-- Name: department_attr_lcl department_attr_lcl_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.department_attr_lcl
    ADD CONSTRAINT department_attr_lcl_pkey PRIMARY KEY (dept_lcl_id);


--
-- Name: department department_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.department
    ADD CONSTRAINT department_pkey PRIMARY KEY (dept_id);


--
-- Name: discount discount_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.discount
    ADD CONSTRAINT discount_pkey PRIMARY KEY (dis_id);


--
-- Name: discount_type discount_type_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.discount_type
    ADD CONSTRAINT discount_type_pkey PRIMARY KEY (dis_typ_id);


--
-- Name: inventory_location inventory_location_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.inventory_location
    ADD CONSTRAINT inventory_location_pkey PRIMARY KEY (inv_loc_id);


--
-- Name: inventory_transaction inventory_transaction_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.inventory_transaction
    ADD CONSTRAINT inventory_transaction_pkey PRIMARY KEY (inv_trx_id);


--
-- Name: inventory_transaction_type inventory_transaction_type_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.inventory_transaction_type
    ADD CONSTRAINT inventory_transaction_type_pkey PRIMARY KEY (inv_trx_typ_id);


--
-- Name: order_line order_line_ord_id_key; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.order_line
    ADD CONSTRAINT order_line_ord_id_key UNIQUE (ord_id);


--
-- Name: order_line order_line_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.order_line
    ADD CONSTRAINT order_line_pkey PRIMARY KEY (ord_id, prd_id, ord_lne_no);


--
-- Name: order orders_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi."order"
    ADD CONSTRAINT orders_pkey PRIMARY KEY (ord_id);


--
-- Name: order orders_pty_id_key; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi."order"
    ADD CONSTRAINT orders_pty_id_key UNIQUE (pty_id);


--
-- Name: organisation organisation_org_id_key; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.organisation
    ADD CONSTRAINT organisation_org_id_key UNIQUE (pty_id);


--
-- Name: party party_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.party
    ADD CONSTRAINT party_pkey PRIMARY KEY (pty_id);


--
-- Name: party_type party_type_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.party_type
    ADD CONSTRAINT party_type_pkey PRIMARY KEY (pty_typ_id);


--
-- Name: party_type party_type_pty_typ_desc_key; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.party_type
    ADD CONSTRAINT party_type_pty_typ_desc_key UNIQUE (pty_typ_desc);


--
-- Name: person person_psn_id_key; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.person
    ADD CONSTRAINT person_psn_id_key UNIQUE (pty_id);


--
-- Name: locale pk_locale; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.locale
    ADD CONSTRAINT pk_locale PRIMARY KEY (lcl_cd);


--
-- Name: accessories_attr_lcl prd_id_lcl_cd_1; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.accessories_attr_lcl
    ADD CONSTRAINT prd_id_lcl_cd_1 UNIQUE (prd_id, lcl_cd);


--
-- Name: department_attr_lcl prd_id_lcl_cd_3; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.department_attr_lcl
    ADD CONSTRAINT prd_id_lcl_cd_3 UNIQUE (dept_id, lcl_cd);


--
-- Name: price price_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.price
    ADD CONSTRAINT price_pkey PRIMARY KEY (prc_id);


--
-- Name: price_type price_type_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.price_type
    ADD CONSTRAINT price_type_pkey PRIMARY KEY (prc_typ_id);


--
-- Name: product_attr_lcl product_attr_lcl_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.product_attr_lcl
    ADD CONSTRAINT product_attr_lcl_pkey PRIMARY KEY (prd_lcl_id);


--
-- Name: product_basic product_basic_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.product_basic
    ADD CONSTRAINT product_basic_pkey PRIMARY KEY (prd_id);


--
-- Name: product_category product_category_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.product_category
    ADD CONSTRAINT product_category_pkey PRIMARY KEY (prd_cat_id);


--
-- Name: product product_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.product
    ADD CONSTRAINT product_pkey PRIMARY KEY (prd_id);


--
-- Name: product_promotion product_promotion_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.product_promotion
    ADD CONSTRAINT product_promotion_pkey PRIMARY KEY (prd_id, prm_id);


--
-- Name: product_rating product_rating_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.product_rating
    ADD CONSTRAINT product_rating_pkey PRIMARY KEY (prd_rat_id);


--
-- Name: product_shipping_attr_lcl product_shipping_attr_lcl_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.product_shipping_attr_lcl
    ADD CONSTRAINT product_shipping_attr_lcl_pkey PRIMARY KEY (prd_lcl_id);


--
-- Name: product_shipping product_shipping_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.product_shipping
    ADD CONSTRAINT product_shipping_pkey PRIMARY KEY (prd_id);


--
-- Name: product_status product_status_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.product_status
    ADD CONSTRAINT product_status_pkey PRIMARY KEY (prd_sts_id);


--
-- Name: product_tag product_tag_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.product_tag
    ADD CONSTRAINT product_tag_pkey PRIMARY KEY (prd_tag_id);


--
-- Name: promotion_attr_lcl promotion_attr_lcl_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.promotion_attr_lcl
    ADD CONSTRAINT promotion_attr_lcl_pkey PRIMARY KEY (prm_lcl_id);


--
-- Name: promotion_order promotion_coupon_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.promotion_order
    ADD CONSTRAINT promotion_coupon_pkey PRIMARY KEY (prm_id);


--
-- Name: promotion_level promotion_level_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.promotion_level
    ADD CONSTRAINT promotion_level_pkey PRIMARY KEY (prm_lvl_id);


--
-- Name: promotion_mechanic promotion_mechanic_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.promotion_mechanic
    ADD CONSTRAINT promotion_mechanic_pkey PRIMARY KEY (prm_mec_id);


--
-- Name: promotion promotion_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.promotion
    ADD CONSTRAINT promotion_pkey PRIMARY KEY (prm_id);


--
-- Name: promotion_product promotion_regular_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.promotion_product
    ADD CONSTRAINT promotion_regular_pkey PRIMARY KEY (prm_id);


--
-- Name: promotion_type promotion_type_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.promotion_type
    ADD CONSTRAINT promotion_type_pkey PRIMARY KEY (prm_typ_id);


--
-- Name: rating rating_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.rating
    ADD CONSTRAINT rating_pkey PRIMARY KEY (rat_id);


--
-- Name: role role_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.role
    ADD CONSTRAINT role_pkey PRIMARY KEY (rle_id);


--
-- Name: role role_pty_id_key; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.role
    ADD CONSTRAINT role_pty_id_key UNIQUE (pty_id);


--
-- Name: role_type role_type_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.role_type
    ADD CONSTRAINT role_type_pkey PRIMARY KEY (rle_typ_id);


--
-- Name: role_type role_type_rle_typ_desc_key; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.role_type
    ADD CONSTRAINT role_type_rle_typ_desc_key UNIQUE (rle_typ_desc);


--
-- Name: stock_on_hand stock_on_hand_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.stock_on_hand
    ADD CONSTRAINT stock_on_hand_pkey PRIMARY KEY (soh_id);


--
-- Name: supplier supplier_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.supplier
    ADD CONSTRAINT supplier_pkey PRIMARY KEY (rle_id);


--
-- Name: tag_attr_lcl tag_attr_lcl_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.tag_attr_lcl
    ADD CONSTRAINT tag_attr_lcl_pkey PRIMARY KEY (tag_lcl_id);


--
-- Name: tag tag_pkey; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.tag
    ADD CONSTRAINT tag_pkey PRIMARY KEY (tag_id);


--
-- Name: bag_item uc_bag_item; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.bag_item
    ADD CONSTRAINT uc_bag_item UNIQUE (bag_id, prd_id);


--
-- Name: bag_item_status uc_bag_sts_cd; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.bag_item_status
    ADD CONSTRAINT uc_bag_sts_cd UNIQUE (bag_item_sts_cd);


--
-- Name: brand uc_bnd_cd; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.brand
    ADD CONSTRAINT uc_bnd_cd UNIQUE (bnd_cd);


--
-- Name: brand_attr_lcl uc_bnd_desc_lcl_cd; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.brand_attr_lcl
    ADD CONSTRAINT uc_bnd_desc_lcl_cd UNIQUE (bnd_desc, lcl_cd);


--
-- Name: brand_attr_lcl uc_bnd_lcl; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.brand_attr_lcl
    ADD CONSTRAINT uc_bnd_lcl UNIQUE (bnd_id, lcl_cd);


--
-- Name: brand_category uc_brand_category; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.brand_category
    ADD CONSTRAINT uc_brand_category UNIQUE (bnd_id, cat_id);


--
-- Name: category uc_cat_cd; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.category
    ADD CONSTRAINT uc_cat_cd UNIQUE (cat_cd);


--
-- Name: category_attr_lcl uc_cat_desc; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.category_attr_lcl
    ADD CONSTRAINT uc_cat_desc UNIQUE (cat_desc, lcl_cd);


--
-- Name: category_attr_lcl uc_cat_lcl; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.category_attr_lcl
    ADD CONSTRAINT uc_cat_lcl UNIQUE (cat_id, lcl_cd);


--
-- Name: category_type uc_cat_typ_cd; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.category_type
    ADD CONSTRAINT uc_cat_typ_cd UNIQUE (cat_typ_cd);


--
-- Name: stock_on_hand uc_prd_id; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.stock_on_hand
    ADD CONSTRAINT uc_prd_id UNIQUE (soh_prd_id);


--
-- Name: product_attr_lcl uc_prd_lcl_1; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.product_attr_lcl
    ADD CONSTRAINT uc_prd_lcl_1 UNIQUE (prd_id, lcl_cd);


--
-- Name: product_shipping_attr_lcl uc_prd_lcl_2; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.product_shipping_attr_lcl
    ADD CONSTRAINT uc_prd_lcl_2 UNIQUE (prd_id, lcl_cd);


--
-- Name: product_rating uc_prd_rat; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.product_rating
    ADD CONSTRAINT uc_prd_rat UNIQUE (prd_id, rat_id, pty_id);


--
-- Name: product_status uc_prd_sts_cd; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.product_status
    ADD CONSTRAINT uc_prd_sts_cd UNIQUE (prd_sts_cd);


--
-- Name: department uc_prd_typ_cd; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.department
    ADD CONSTRAINT uc_prd_typ_cd UNIQUE (dept_cd);


--
-- Name: promotion uc_prm_cd; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.promotion
    ADD CONSTRAINT uc_prm_cd UNIQUE (prm_cd);


--
-- Name: promotion_attr_lcl uc_prm_lcl_1; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.promotion_attr_lcl
    ADD CONSTRAINT uc_prm_lcl_1 UNIQUE (prm_id, lcl_cd);


--
-- Name: promotion_level uc_prm_lvl_cd; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.promotion_level
    ADD CONSTRAINT uc_prm_lvl_cd UNIQUE (prm_lvl_cd);


--
-- Name: promotion_mechanic uc_prm_mec_cd; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.promotion_mechanic
    ADD CONSTRAINT uc_prm_mec_cd UNIQUE (prm_mec_cd);


--
-- Name: promotion_type uc_prm_typ_cd; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.promotion_type
    ADD CONSTRAINT uc_prm_typ_cd UNIQUE (prm_typ_cd);


--
-- Name: product_category uc_product_category; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.product_category
    ADD CONSTRAINT uc_product_category UNIQUE (prd_id, cat_id);


--
-- Name: product_tag uc_product_tag; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.product_tag
    ADD CONSTRAINT uc_product_tag UNIQUE (prd_id, tag_id);


--
-- Name: product uc_product_upc_cd; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.product
    ADD CONSTRAINT uc_product_upc_cd UNIQUE (upc_cd);


--
-- Name: tag uc_tag_cd; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.tag
    ADD CONSTRAINT uc_tag_cd UNIQUE (tag_cd);


--
-- Name: tag_attr_lcl uc_tag_desc; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.tag_attr_lcl
    ADD CONSTRAINT uc_tag_desc UNIQUE (tag_desc, lcl_cd);


--
-- Name: tag_attr_lcl uc_tag_lcl; Type: CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.tag_attr_lcl
    ADD CONSTRAINT uc_tag_lcl UNIQUE (tag_id, lcl_cd);


--
-- Name: fki_product_attr_lcl_prd_id_fkey; Type: INDEX; Schema: mochi; Owner: mochidb_owner
--

CREATE INDEX fki_product_attr_lcl_prd_id_fkey ON mochi.product_attr_lcl USING btree (prd_id);


--
-- Name: fki_product_shipping_attr_lcl_prd_id_fkey; Type: INDEX; Schema: mochi; Owner: mochidb_owner
--

CREATE INDEX fki_product_shipping_attr_lcl_prd_id_fkey ON mochi.product_shipping_attr_lcl USING btree (prd_id);


--
-- Name: fki_promotion_attr_lcl_prm_id_fkey; Type: INDEX; Schema: mochi; Owner: mochidb_owner
--

CREATE INDEX fki_promotion_attr_lcl_prm_id_fkey ON mochi.promotion_attr_lcl USING btree (prm_id);


--
-- Name: role_role_typ_id_role_start_dttm_party_id_key; Type: INDEX; Schema: mochi; Owner: mochidb_owner
--

CREATE UNIQUE INDEX role_role_typ_id_role_start_dttm_party_id_key ON mochi.role USING btree (rle_typ_id, rle_start_dt, pty_id);


--
-- Name: accessories_attr_lcl accessories_attr_lcl_lcl_cd_fkey; Type: FK CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.accessories_attr_lcl
    ADD CONSTRAINT accessories_attr_lcl_lcl_cd_fkey FOREIGN KEY (lcl_cd) REFERENCES mochi.locale(lcl_cd);


--
-- Name: address address_addr_typ_id_address_type_addr_typ_id_fkey; Type: FK CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.address
    ADD CONSTRAINT address_addr_typ_id_address_type_addr_typ_id_fkey FOREIGN KEY (addr_typ_id) REFERENCES mochi.address_type(addr_typ_id);


--
-- Name: address address_pty_id_party_pty_id_fkey; Type: FK CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.address
    ADD CONSTRAINT address_pty_id_party_pty_id_fkey FOREIGN KEY (pty_id) REFERENCES mochi.party(pty_id);


--
-- Name: bag_item bag_item_bag_id_bag_bag_id_fkey; Type: FK CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.bag_item
    ADD CONSTRAINT bag_item_bag_id_bag_bag_id_fkey FOREIGN KEY (bag_id) REFERENCES mochi.bag(bag_id);


--
-- Name: bag_item_disc bag_item_disc_bag_item_id_bag_item_bag_item_id_fkey; Type: FK CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.bag_item_disc
    ADD CONSTRAINT bag_item_disc_bag_item_id_bag_item_bag_item_id_fkey FOREIGN KEY (bag_item_id) REFERENCES mochi.bag_item(bag_item_id);


--
-- Name: bag_item bag_item_sts_id_bag_item_sts_bag_item_sts_id_fkey; Type: FK CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.bag_item
    ADD CONSTRAINT bag_item_sts_id_bag_item_sts_bag_item_sts_id_fkey FOREIGN KEY (bag_item_sts_id) REFERENCES mochi.bag_item_status(bag_item_sts_id);


--
-- Name: bag bag_prm_id_promotion_order_prm_id_fkey; Type: FK CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.bag
    ADD CONSTRAINT bag_prm_id_promotion_order_prm_id_fkey FOREIGN KEY (prm_id) REFERENCES mochi.promotion_order(prm_id);


--
-- Name: bag bag_pty_id_party_pty_id_fkey; Type: FK CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.bag
    ADD CONSTRAINT bag_pty_id_party_pty_id_fkey FOREIGN KEY (pty_id) REFERENCES mochi.party(pty_id);


--
-- Name: brand_attr_lcl brand_attr_lcl_bnd_id_fkey; Type: FK CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.brand_attr_lcl
    ADD CONSTRAINT brand_attr_lcl_bnd_id_fkey FOREIGN KEY (bnd_id) REFERENCES mochi.brand(bnd_id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: brand_attr_lcl brand_attr_lcl_lcl_cd_fkey; Type: FK CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.brand_attr_lcl
    ADD CONSTRAINT brand_attr_lcl_lcl_cd_fkey FOREIGN KEY (lcl_cd) REFERENCES mochi.locale(lcl_cd);


--
-- Name: brand_category brand_category_bnd_id_brand_bnd_id_fkey; Type: FK CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.brand_category
    ADD CONSTRAINT brand_category_bnd_id_brand_bnd_id_fkey FOREIGN KEY (bnd_id) REFERENCES mochi.brand(bnd_id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: brand_category brand_category_cat_id_category_cat_id_fkey; Type: FK CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.brand_category
    ADD CONSTRAINT brand_category_cat_id_category_cat_id_fkey FOREIGN KEY (cat_id) REFERENCES mochi.category(cat_id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: category_attr_lcl category_attr_lcl_cat_id_fkey; Type: FK CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.category_attr_lcl
    ADD CONSTRAINT category_attr_lcl_cat_id_fkey FOREIGN KEY (cat_id) REFERENCES mochi.category(cat_id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: category_attr_lcl category_attr_lcl_lcl_cd_fkey; Type: FK CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.category_attr_lcl
    ADD CONSTRAINT category_attr_lcl_lcl_cd_fkey FOREIGN KEY (lcl_cd) REFERENCES mochi.locale(lcl_cd);


--
-- Name: category_brand category_brand_cat_id_category_cat_id_fkey; Type: FK CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.category_brand
    ADD CONSTRAINT category_brand_cat_id_category_cat_id_fkey FOREIGN KEY (cat_id) REFERENCES mochi.category(cat_id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: category_product category_product_cat_id_category_cat_id_fkey; Type: FK CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.category_product
    ADD CONSTRAINT category_product_cat_id_category_cat_id_fkey FOREIGN KEY (cat_id) REFERENCES mochi.category(cat_id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: customer customer_role_id_fkey; Type: FK CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.customer
    ADD CONSTRAINT customer_role_id_fkey FOREIGN KEY (rle_id) REFERENCES mochi.role(rle_id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: department_attr_lcl department_attr_lcl_dept_id_fkey; Type: FK CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.department_attr_lcl
    ADD CONSTRAINT department_attr_lcl_dept_id_fkey FOREIGN KEY (dept_id) REFERENCES mochi.department(dept_id);


--
-- Name: department_attr_lcl department_attr_lcl_lcl_cd_fkey; Type: FK CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.department_attr_lcl
    ADD CONSTRAINT department_attr_lcl_lcl_cd_fkey FOREIGN KEY (lcl_cd) REFERENCES mochi.locale(lcl_cd);


--
-- Name: inventory_transaction inventory_transaction_ccy_id_currency_ccy_id; Type: FK CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.inventory_transaction
    ADD CONSTRAINT inventory_transaction_ccy_id_currency_ccy_id FOREIGN KEY (inv_ccy_id) REFERENCES mochi.currency(ccy_id);


--
-- Name: inventory_transaction inventory_transaction_inv_loc_id_inventory_location_inv_loc_id; Type: FK CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.inventory_transaction
    ADD CONSTRAINT inventory_transaction_inv_loc_id_inventory_location_inv_loc_id FOREIGN KEY (inv_loc_id) REFERENCES mochi.inventory_location(inv_loc_id);


--
-- Name: inventory_transaction inventory_transaction_inv_prd_id_product_prd_id; Type: FK CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.inventory_transaction
    ADD CONSTRAINT inventory_transaction_inv_prd_id_product_prd_id FOREIGN KEY (inv_prd_id) REFERENCES mochi.product(prd_id);


--
-- Name: inventory_transaction inventory_transaction_inv_pty_id_supplier_pty_id; Type: FK CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.inventory_transaction
    ADD CONSTRAINT inventory_transaction_inv_pty_id_supplier_pty_id FOREIGN KEY (inv_pty_id) REFERENCES mochi.party(pty_id);


--
-- Name: inventory_transaction inventory_transaction_inv_trx_typ_id_inventory_transaction_type; Type: FK CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.inventory_transaction
    ADD CONSTRAINT inventory_transaction_inv_trx_typ_id_inventory_transaction_type FOREIGN KEY (inv_trx_typ_id) REFERENCES mochi.inventory_transaction_type(inv_trx_typ_id);


--
-- Name: order_line order_line_order_id_fkey; Type: FK CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.order_line
    ADD CONSTRAINT order_line_order_id_fkey FOREIGN KEY (ord_id) REFERENCES mochi."order"(ord_id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: order_line order_line_product_id_fkey; Type: FK CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.order_line
    ADD CONSTRAINT order_line_product_id_fkey FOREIGN KEY (prd_id) REFERENCES mochi.product(prd_id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: order orders_party_id_fkey; Type: FK CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi."order"
    ADD CONSTRAINT orders_party_id_fkey FOREIGN KEY (pty_id) REFERENCES mochi.party(pty_id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: organisation organisation_org_id_fkey; Type: FK CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.organisation
    ADD CONSTRAINT organisation_org_id_fkey FOREIGN KEY (pty_id) REFERENCES mochi.party(pty_id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: party party_pty_typ_id_fkey; Type: FK CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.party
    ADD CONSTRAINT party_pty_typ_id_fkey FOREIGN KEY (pty_typ_id) REFERENCES mochi.party_type(pty_typ_id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: person person_person_id_fkey; Type: FK CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.person
    ADD CONSTRAINT person_person_id_fkey FOREIGN KEY (pty_id) REFERENCES mochi.party(pty_id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: price price_currency_id_fkey; Type: FK CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.price
    ADD CONSTRAINT price_currency_id_fkey FOREIGN KEY (ccy_id) REFERENCES mochi.currency(ccy_id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: price price_product_id_fkey; Type: FK CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.price
    ADD CONSTRAINT price_product_id_fkey FOREIGN KEY (prd_id) REFERENCES mochi.product(prd_id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: product_attr_lcl product_attr_lcl_lcl_cd_fkey; Type: FK CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.product_attr_lcl
    ADD CONSTRAINT product_attr_lcl_lcl_cd_fkey FOREIGN KEY (lcl_cd) REFERENCES mochi.locale(lcl_cd);


--
-- Name: product_attr_lcl product_attr_lcl_prd_id_fkey; Type: FK CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.product_attr_lcl
    ADD CONSTRAINT product_attr_lcl_prd_id_fkey FOREIGN KEY (prd_id) REFERENCES mochi.product(prd_id);


--
-- Name: product_basic product_basic_prd_id_fkey; Type: FK CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.product_basic
    ADD CONSTRAINT product_basic_prd_id_fkey FOREIGN KEY (prd_id) REFERENCES mochi.product(prd_id);


--
-- Name: product_category product_category_cat_id_category_cat_id_fkey; Type: FK CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.product_category
    ADD CONSTRAINT product_category_cat_id_category_cat_id_fkey FOREIGN KEY (cat_id) REFERENCES mochi.category(cat_id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: product_category product_category_prd_id_product_prd_id_fkey; Type: FK CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.product_category
    ADD CONSTRAINT product_category_prd_id_product_prd_id_fkey FOREIGN KEY (prd_id) REFERENCES mochi.product(prd_id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: product_shipping_attr_lcl product_shipping_attr_lcl_lcl_cd_fkey; Type: FK CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.product_shipping_attr_lcl
    ADD CONSTRAINT product_shipping_attr_lcl_lcl_cd_fkey FOREIGN KEY (lcl_cd) REFERENCES mochi.locale(lcl_cd);


--
-- Name: product_shipping_attr_lcl product_shipping_attr_lcl_prd_id_fkey; Type: FK CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.product_shipping_attr_lcl
    ADD CONSTRAINT product_shipping_attr_lcl_prd_id_fkey FOREIGN KEY (prd_id) REFERENCES mochi.product_shipping(prd_id);


--
-- Name: product_shipping product_shipping_prd_id_fkey; Type: FK CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.product_shipping
    ADD CONSTRAINT product_shipping_prd_id_fkey FOREIGN KEY (prd_id) REFERENCES mochi.product(prd_id);


--
-- Name: product product_sts_id_product_status_sts_id_fkey; Type: FK CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.product
    ADD CONSTRAINT product_sts_id_product_status_sts_id_fkey FOREIGN KEY (prd_sts_id) REFERENCES mochi.product_status(prd_sts_id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: product_tag product_tag_prd_id_product_prd_id_fkey; Type: FK CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.product_tag
    ADD CONSTRAINT product_tag_prd_id_product_prd_id_fkey FOREIGN KEY (prd_id) REFERENCES mochi.product(prd_id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: product_tag product_tag_tag_id_tag_tag_id_fkey; Type: FK CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.product_tag
    ADD CONSTRAINT product_tag_tag_id_tag_tag_id_fkey FOREIGN KEY (tag_id) REFERENCES mochi.tag(tag_id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: product product_typ_id_product_type_typ_id_fkey; Type: FK CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.product
    ADD CONSTRAINT product_typ_id_product_type_typ_id_fkey FOREIGN KEY (dept_id) REFERENCES mochi.department(dept_id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: promotion_attr_lcl promotion_attr_lcl_lcl_cd_fkey; Type: FK CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.promotion_attr_lcl
    ADD CONSTRAINT promotion_attr_lcl_lcl_cd_fkey FOREIGN KEY (lcl_cd) REFERENCES mochi.locale(lcl_cd);


--
-- Name: promotion_attr_lcl promotion_attr_lcl_prm_id_fkey; Type: FK CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.promotion_attr_lcl
    ADD CONSTRAINT promotion_attr_lcl_prm_id_fkey FOREIGN KEY (prm_id) REFERENCES mochi.promotion(prm_id);


--
-- Name: promotion_order promotion_coupon_promotion_prm_id_fkey; Type: FK CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.promotion_order
    ADD CONSTRAINT promotion_coupon_promotion_prm_id_fkey FOREIGN KEY (prm_id) REFERENCES mochi.promotion(prm_id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: promotion_product promotion_regular_promotion_prm_id_fkey; Type: FK CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.promotion_product
    ADD CONSTRAINT promotion_regular_promotion_prm_id_fkey FOREIGN KEY (prm_id) REFERENCES mochi.promotion(prm_id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: role role_party_id_fkey; Type: FK CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.role
    ADD CONSTRAINT role_party_id_fkey FOREIGN KEY (pty_id) REFERENCES mochi.party(pty_id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: role role_role_typ_id_fkey; Type: FK CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.role
    ADD CONSTRAINT role_role_typ_id_fkey FOREIGN KEY (rle_typ_id) REFERENCES mochi.role_type(rle_typ_id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: stock_on_hand stock_on_hand_prd_id_fkey; Type: FK CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.stock_on_hand
    ADD CONSTRAINT stock_on_hand_prd_id_fkey FOREIGN KEY (soh_prd_id) REFERENCES mochi.product(prd_id);


--
-- Name: supplier supplier_role_id_fkey; Type: FK CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.supplier
    ADD CONSTRAINT supplier_role_id_fkey FOREIGN KEY (rle_id) REFERENCES mochi.role(rle_id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: tag_attr_lcl tag_attr_lcl_lcl_cd_fkey; Type: FK CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.tag_attr_lcl
    ADD CONSTRAINT tag_attr_lcl_lcl_cd_fkey FOREIGN KEY (lcl_cd) REFERENCES mochi.locale(lcl_cd);


--
-- Name: tag_attr_lcl tag_attr_lcl_tag_id_fkey; Type: FK CONSTRAINT; Schema: mochi; Owner: mochidb_owner
--

ALTER TABLE ONLY mochi.tag_attr_lcl
    ADD CONSTRAINT tag_attr_lcl_tag_id_fkey FOREIGN KEY (tag_id) REFERENCES mochi.tag(tag_id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: SCHEMA mochi; Type: ACL; Schema: -; Owner: mochidb_owner
--

GRANT USAGE ON SCHEMA mochi TO mochi_app;
GRANT USAGE ON SCHEMA mochi TO security_app;
GRANT ALL ON SCHEMA mochi TO security_owner;


--
-- Name: SEQUENCE accessories_attr_lcl_prd_lcl_id_seq; Type: ACL; Schema: mochi; Owner: postgres
--

GRANT ALL ON SEQUENCE mochi.accessories_attr_lcl_prd_lcl_id_seq TO mochi_app;
GRANT ALL ON SEQUENCE mochi.accessories_attr_lcl_prd_lcl_id_seq TO mochidb_owner;


--
-- Name: TABLE accessories_attr_lcl; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE mochi.accessories_attr_lcl TO mochi_app;


--
-- Name: SEQUENCE address_addr_id_seq; Type: ACL; Schema: mochi; Owner: postgres
--

GRANT ALL ON SEQUENCE mochi.address_addr_id_seq TO mochi_app;
GRANT ALL ON SEQUENCE mochi.address_addr_id_seq TO mochidb_owner;


--
-- Name: TABLE address; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE mochi.address TO mochi_app;


--
-- Name: SEQUENCE address_type_addr_typ_id_seq; Type: ACL; Schema: mochi; Owner: postgres
--

GRANT ALL ON SEQUENCE mochi.address_type_addr_typ_id_seq TO mochi_app;
GRANT ALL ON SEQUENCE mochi.address_type_addr_typ_id_seq TO mochidb_owner;


--
-- Name: TABLE address_type; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE mochi.address_type TO mochi_app;


--
-- Name: SEQUENCE bag_bag_id_seq; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT ALL ON SEQUENCE mochi.bag_bag_id_seq TO mochi_app;


--
-- Name: TABLE bag; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE mochi.bag TO mochi_app;


--
-- Name: SEQUENCE bag_item_bag_item_id_seq; Type: ACL; Schema: mochi; Owner: postgres
--

GRANT ALL ON SEQUENCE mochi.bag_item_bag_item_id_seq TO mochi_app;
GRANT ALL ON SEQUENCE mochi.bag_item_bag_item_id_seq TO mochidb_owner;


--
-- Name: TABLE bag_item; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE mochi.bag_item TO mochi_app;


--
-- Name: SEQUENCE bag_item_disc_bag_item_disc_id_seq; Type: ACL; Schema: mochi; Owner: postgres
--

GRANT ALL ON SEQUENCE mochi.bag_item_disc_bag_item_disc_id_seq TO mochi_app;
GRANT ALL ON SEQUENCE mochi.bag_item_disc_bag_item_disc_id_seq TO mochidb_owner;


--
-- Name: TABLE bag_item_disc; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE mochi.bag_item_disc TO mochi_app;


--
-- Name: SEQUENCE bag_item_status_bag_item_sts_id_seq; Type: ACL; Schema: mochi; Owner: postgres
--

GRANT ALL ON SEQUENCE mochi.bag_item_status_bag_item_sts_id_seq TO mochi_app;
GRANT ALL ON SEQUENCE mochi.bag_item_status_bag_item_sts_id_seq TO mochidb_owner;


--
-- Name: TABLE bag_item_status; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE mochi.bag_item_status TO mochi_app;


--
-- Name: SEQUENCE brand_bnd_id_seq; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT ALL ON SEQUENCE mochi.brand_bnd_id_seq TO mochi_app;


--
-- Name: TABLE brand; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE mochi.brand TO mochi_app;


--
-- Name: SEQUENCE brand_attr_lcl_bnd_lcl_id_seq; Type: ACL; Schema: mochi; Owner: postgres
--

GRANT ALL ON SEQUENCE mochi.brand_attr_lcl_bnd_lcl_id_seq TO mochi_app;
GRANT ALL ON SEQUENCE mochi.brand_attr_lcl_bnd_lcl_id_seq TO mochidb_owner;


--
-- Name: TABLE brand_attr_lcl; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE mochi.brand_attr_lcl TO mochi_app;


--
-- Name: SEQUENCE brand_attr_lcl_bnd_id_seq; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT ALL ON SEQUENCE mochi.brand_attr_lcl_bnd_id_seq TO mochi_app;


--
-- Name: SEQUENCE brand_category_bnd_cat_id_seq; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT ALL ON SEQUENCE mochi.brand_category_bnd_cat_id_seq TO mochi_app;


--
-- Name: TABLE brand_category; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE mochi.brand_category TO mochi_app;


--
-- Name: SEQUENCE brand_promotion_bnd_id_seq; Type: ACL; Schema: mochi; Owner: postgres
--

GRANT ALL ON SEQUENCE mochi.brand_promotion_bnd_id_seq TO mochi_app;
GRANT ALL ON SEQUENCE mochi.brand_promotion_bnd_id_seq TO mochidb_owner;


--
-- Name: SEQUENCE brand_promotion_prm_id_seq; Type: ACL; Schema: mochi; Owner: postgres
--

GRANT ALL ON SEQUENCE mochi.brand_promotion_prm_id_seq TO mochi_app;
GRANT ALL ON SEQUENCE mochi.brand_promotion_prm_id_seq TO mochidb_owner;


--
-- Name: SEQUENCE category_cat_id_seq; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT ALL ON SEQUENCE mochi.category_cat_id_seq TO mochi_app;


--
-- Name: TABLE category; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE mochi.category TO mochi_app;


--
-- Name: SEQUENCE category_attr_lcl_cat_lcl_id_seq; Type: ACL; Schema: mochi; Owner: postgres
--

GRANT ALL ON SEQUENCE mochi.category_attr_lcl_cat_lcl_id_seq TO mochi_app;
GRANT ALL ON SEQUENCE mochi.category_attr_lcl_cat_lcl_id_seq TO mochidb_owner;


--
-- Name: TABLE category_attr_lcl; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE mochi.category_attr_lcl TO mochi_app;


--
-- Name: SEQUENCE category_brand_cat_id_seq; Type: ACL; Schema: mochi; Owner: postgres
--

GRANT ALL ON SEQUENCE mochi.category_brand_cat_id_seq TO mochi_app;
GRANT ALL ON SEQUENCE mochi.category_brand_cat_id_seq TO mochidb_owner;


--
-- Name: TABLE category_brand; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE mochi.category_brand TO mochi_app;


--
-- Name: SEQUENCE category_product_cat_id_seq; Type: ACL; Schema: mochi; Owner: postgres
--

GRANT ALL ON SEQUENCE mochi.category_product_cat_id_seq TO mochi_app;
GRANT ALL ON SEQUENCE mochi.category_product_cat_id_seq TO mochidb_owner;


--
-- Name: TABLE category_product; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE mochi.category_product TO mochi_app;


--
-- Name: SEQUENCE category_promotion_cat_id_seq; Type: ACL; Schema: mochi; Owner: postgres
--

GRANT ALL ON SEQUENCE mochi.category_promotion_cat_id_seq TO mochi_app;
GRANT ALL ON SEQUENCE mochi.category_promotion_cat_id_seq TO mochidb_owner;


--
-- Name: SEQUENCE category_promotion_prm_id_seq; Type: ACL; Schema: mochi; Owner: postgres
--

GRANT ALL ON SEQUENCE mochi.category_promotion_prm_id_seq TO mochi_app;
GRANT ALL ON SEQUENCE mochi.category_promotion_prm_id_seq TO mochidb_owner;


--
-- Name: TABLE category_promotion; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE mochi.category_promotion TO mochi_app;


--
-- Name: SEQUENCE category_type_cat_typ_id_seq; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT ALL ON SEQUENCE mochi.category_type_cat_typ_id_seq TO mochi_app;


--
-- Name: TABLE category_type; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE mochi.category_type TO mochi_app;


--
-- Name: SEQUENCE currency_ccy_id_seq; Type: ACL; Schema: mochi; Owner: postgres
--

GRANT ALL ON SEQUENCE mochi.currency_ccy_id_seq TO mochi_app;
GRANT ALL ON SEQUENCE mochi.currency_ccy_id_seq TO mochidb_owner;


--
-- Name: TABLE currency; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE mochi.currency TO mochi_app;


--
-- Name: SEQUENCE customer_cst_num_seq; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT ALL ON SEQUENCE mochi.customer_cst_num_seq TO mochi_app;


--
-- Name: SEQUENCE customer_rle_id_seq; Type: ACL; Schema: mochi; Owner: postgres
--

GRANT ALL ON SEQUENCE mochi.customer_rle_id_seq TO mochi_app;
GRANT ALL ON SEQUENCE mochi.customer_rle_id_seq TO mochidb_owner;


--
-- Name: TABLE customer; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT SELECT ON TABLE mochi.customer TO security_app;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE mochi.customer TO mochi_app;


--
-- Name: SEQUENCE department_dept_id_seq; Type: ACL; Schema: mochi; Owner: postgres
--

GRANT ALL ON SEQUENCE mochi.department_dept_id_seq TO mochi_app;
GRANT ALL ON SEQUENCE mochi.department_dept_id_seq TO mochidb_owner;


--
-- Name: TABLE department; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE mochi.department TO mochi_app;


--
-- Name: SEQUENCE department_attr_lcl_dept_lcl_id_seq; Type: ACL; Schema: mochi; Owner: postgres
--

GRANT ALL ON SEQUENCE mochi.department_attr_lcl_dept_lcl_id_seq TO mochi_app;
GRANT ALL ON SEQUENCE mochi.department_attr_lcl_dept_lcl_id_seq TO mochidb_owner;


--
-- Name: TABLE department_attr_lcl; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE mochi.department_attr_lcl TO mochi_app;


--
-- Name: SEQUENCE discount_dis_id_seq; Type: ACL; Schema: mochi; Owner: postgres
--

GRANT ALL ON SEQUENCE mochi.discount_dis_id_seq TO mochi_app;
GRANT ALL ON SEQUENCE mochi.discount_dis_id_seq TO mochidb_owner;


--
-- Name: TABLE discount; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE mochi.discount TO mochi_app;


--
-- Name: SEQUENCE discount_type_dis_typ_id_seq; Type: ACL; Schema: mochi; Owner: postgres
--

GRANT ALL ON SEQUENCE mochi.discount_type_dis_typ_id_seq TO mochi_app;
GRANT ALL ON SEQUENCE mochi.discount_type_dis_typ_id_seq TO mochidb_owner;


--
-- Name: TABLE discount_type; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE mochi.discount_type TO mochi_app;


--
-- Name: SEQUENCE inventory_location_inv_loc_id_seq; Type: ACL; Schema: mochi; Owner: postgres
--

GRANT ALL ON SEQUENCE mochi.inventory_location_inv_loc_id_seq TO mochi_app;
GRANT ALL ON SEQUENCE mochi.inventory_location_inv_loc_id_seq TO mochidb_owner;


--
-- Name: TABLE inventory_location; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE mochi.inventory_location TO mochi_app;


--
-- Name: SEQUENCE inventory_transaction_inv_trx_id_seq; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT ALL ON SEQUENCE mochi.inventory_transaction_inv_trx_id_seq TO mochi_app;


--
-- Name: TABLE inventory_transaction; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE mochi.inventory_transaction TO mochi_app;


--
-- Name: SEQUENCE inventory_transaction_type_inv_trx_typ_id_seq; Type: ACL; Schema: mochi; Owner: postgres
--

GRANT ALL ON SEQUENCE mochi.inventory_transaction_type_inv_trx_typ_id_seq TO mochi_app;
GRANT ALL ON SEQUENCE mochi.inventory_transaction_type_inv_trx_typ_id_seq TO mochidb_owner;


--
-- Name: TABLE inventory_transaction_type; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE mochi.inventory_transaction_type TO mochi_app;


--
-- Name: SEQUENCE locale_lcl_cd_seq; Type: ACL; Schema: mochi; Owner: postgres
--

GRANT ALL ON SEQUENCE mochi.locale_lcl_cd_seq TO mochi_app;
GRANT ALL ON SEQUENCE mochi.locale_lcl_cd_seq TO mochidb_owner;


--
-- Name: TABLE locale; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE mochi.locale TO mochi_app;


--
-- Name: SEQUENCE order_ord_id_seq; Type: ACL; Schema: mochi; Owner: postgres
--

GRANT ALL ON SEQUENCE mochi.order_ord_id_seq TO mochi_app;
GRANT ALL ON SEQUENCE mochi.order_ord_id_seq TO mochidb_owner;


--
-- Name: TABLE "order"; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE mochi."order" TO mochi_app;


--
-- Name: SEQUENCE order_line_ord_id_seq; Type: ACL; Schema: mochi; Owner: postgres
--

GRANT ALL ON SEQUENCE mochi.order_line_ord_id_seq TO mochi_app;
GRANT ALL ON SEQUENCE mochi.order_line_ord_id_seq TO mochidb_owner;


--
-- Name: SEQUENCE order_line_ord_lne_no_seq; Type: ACL; Schema: mochi; Owner: postgres
--

GRANT ALL ON SEQUENCE mochi.order_line_ord_lne_no_seq TO mochi_app;
GRANT ALL ON SEQUENCE mochi.order_line_ord_lne_no_seq TO mochidb_owner;


--
-- Name: SEQUENCE order_line_prd_id_seq; Type: ACL; Schema: mochi; Owner: postgres
--

GRANT ALL ON SEQUENCE mochi.order_line_prd_id_seq TO mochi_app;
GRANT ALL ON SEQUENCE mochi.order_line_prd_id_seq TO mochidb_owner;


--
-- Name: TABLE order_line; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE mochi.order_line TO mochi_app;


--
-- Name: TABLE organisation; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT SELECT ON TABLE mochi.organisation TO security_app;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE mochi.organisation TO mochi_app;


--
-- Name: TABLE party; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT SELECT ON TABLE mochi.party TO security_app;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE mochi.party TO mochi_app;


--
-- Name: SEQUENCE party_pty_id_seq; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT ALL ON SEQUENCE mochi.party_pty_id_seq TO mochi_app;


--
-- Name: TABLE party_type; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT SELECT ON TABLE mochi.party_type TO security_app;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE mochi.party_type TO mochi_app;


--
-- Name: SEQUENCE party_type_pty_typ_id_seq; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT ALL ON SEQUENCE mochi.party_type_pty_typ_id_seq TO mochi_app;


--
-- Name: TABLE person; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT SELECT ON TABLE mochi.person TO security_app;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE mochi.person TO mochi_app;


--
-- Name: SEQUENCE price_prc_id_seq; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT ALL ON SEQUENCE mochi.price_prc_id_seq TO mochi_app;


--
-- Name: TABLE price; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE mochi.price TO mochi_app;


--
-- Name: SEQUENCE price_type_prc_typ_id_seq; Type: ACL; Schema: mochi; Owner: postgres
--

GRANT ALL ON SEQUENCE mochi.price_type_prc_typ_id_seq TO mochi_app;
GRANT ALL ON SEQUENCE mochi.price_type_prc_typ_id_seq TO mochidb_owner;


--
-- Name: TABLE price_type; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE mochi.price_type TO mochi_app;


--
-- Name: SEQUENCE product_prd_id_seq; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT ALL ON SEQUENCE mochi.product_prd_id_seq TO mochi_app;


--
-- Name: TABLE product; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE mochi.product TO mochi_app;


--
-- Name: SEQUENCE product_attr_lcl_prd_lcl_id_seq; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT ALL ON SEQUENCE mochi.product_attr_lcl_prd_lcl_id_seq TO mochi_app;


--
-- Name: TABLE product_attr_lcl; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE mochi.product_attr_lcl TO mochi_app;


--
-- Name: SEQUENCE product_basic_prd_id_seq; Type: ACL; Schema: mochi; Owner: postgres
--

GRANT ALL ON SEQUENCE mochi.product_basic_prd_id_seq TO mochi_app;
GRANT ALL ON SEQUENCE mochi.product_basic_prd_id_seq TO mochidb_owner;


--
-- Name: TABLE product_basic; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE mochi.product_basic TO mochi_app;


--
-- Name: SEQUENCE product_category_prd_cat_id_seq; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT ALL ON SEQUENCE mochi.product_category_prd_cat_id_seq TO mochi_app;


--
-- Name: TABLE product_category; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE mochi.product_category TO mochi_app;


--
-- Name: SEQUENCE product_promotion_prd_id_seq; Type: ACL; Schema: mochi; Owner: postgres
--

GRANT ALL ON SEQUENCE mochi.product_promotion_prd_id_seq TO mochi_app;
GRANT ALL ON SEQUENCE mochi.product_promotion_prd_id_seq TO mochidb_owner;


--
-- Name: SEQUENCE product_promotion_prm_id_seq; Type: ACL; Schema: mochi; Owner: postgres
--

GRANT ALL ON SEQUENCE mochi.product_promotion_prm_id_seq TO mochi_app;
GRANT ALL ON SEQUENCE mochi.product_promotion_prm_id_seq TO mochidb_owner;


--
-- Name: TABLE product_promotion; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE mochi.product_promotion TO mochi_app;


--
-- Name: SEQUENCE product_rating_prd_rat_id_seq; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT ALL ON SEQUENCE mochi.product_rating_prd_rat_id_seq TO mochi_app;


--
-- Name: TABLE product_rating; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE mochi.product_rating TO mochi_app;


--
-- Name: SEQUENCE product_shipping_prd_id_seq; Type: ACL; Schema: mochi; Owner: postgres
--

GRANT ALL ON SEQUENCE mochi.product_shipping_prd_id_seq TO mochi_app;
GRANT ALL ON SEQUENCE mochi.product_shipping_prd_id_seq TO mochidb_owner;


--
-- Name: TABLE product_shipping; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE mochi.product_shipping TO mochi_app;


--
-- Name: SEQUENCE product_shipping_attr_lcl_prd_lcl_id_seq; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT ALL ON SEQUENCE mochi.product_shipping_attr_lcl_prd_lcl_id_seq TO mochi_app;


--
-- Name: TABLE product_shipping_attr_lcl; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE mochi.product_shipping_attr_lcl TO mochi_app;


--
-- Name: SEQUENCE product_status_prd_sts_id_seq; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT ALL ON SEQUENCE mochi.product_status_prd_sts_id_seq TO mochi_app;


--
-- Name: TABLE product_status; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE mochi.product_status TO mochi_app;


--
-- Name: TABLE product_supplier; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE mochi.product_supplier TO mochi_app;


--
-- Name: SEQUENCE product_tag_prd_tag_id_seq; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT ALL ON SEQUENCE mochi.product_tag_prd_tag_id_seq TO mochi_app;


--
-- Name: TABLE product_tag; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE mochi.product_tag TO mochi_app;


--
-- Name: SEQUENCE promotion_prm_id_seq; Type: ACL; Schema: mochi; Owner: postgres
--

GRANT ALL ON SEQUENCE mochi.promotion_prm_id_seq TO mochi_app;
GRANT ALL ON SEQUENCE mochi.promotion_prm_id_seq TO mochidb_owner;


--
-- Name: TABLE promotion; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE mochi.promotion TO mochi_app;


--
-- Name: SEQUENCE promotion_attr_lcl_prm_lcl_id_seq; Type: ACL; Schema: mochi; Owner: postgres
--

GRANT ALL ON SEQUENCE mochi.promotion_attr_lcl_prm_lcl_id_seq TO mochi_app;
GRANT ALL ON SEQUENCE mochi.promotion_attr_lcl_prm_lcl_id_seq TO mochidb_owner;


--
-- Name: TABLE promotion_attr_lcl; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE mochi.promotion_attr_lcl TO mochi_app;


--
-- Name: SEQUENCE promotion_level_prm_lvl_id_seq; Type: ACL; Schema: mochi; Owner: postgres
--

GRANT ALL ON SEQUENCE mochi.promotion_level_prm_lvl_id_seq TO mochi_app;
GRANT ALL ON SEQUENCE mochi.promotion_level_prm_lvl_id_seq TO mochidb_owner;


--
-- Name: TABLE promotion_level; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE mochi.promotion_level TO mochi_app;


--
-- Name: SEQUENCE promotion_mechanic_prm_mec_id_seq; Type: ACL; Schema: mochi; Owner: postgres
--

GRANT ALL ON SEQUENCE mochi.promotion_mechanic_prm_mec_id_seq TO mochi_app;
GRANT ALL ON SEQUENCE mochi.promotion_mechanic_prm_mec_id_seq TO mochidb_owner;


--
-- Name: TABLE promotion_mechanic; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE mochi.promotion_mechanic TO mochi_app;


--
-- Name: SEQUENCE promotion_order_prm_id_seq; Type: ACL; Schema: mochi; Owner: postgres
--

GRANT ALL ON SEQUENCE mochi.promotion_order_prm_id_seq TO mochi_app;
GRANT ALL ON SEQUENCE mochi.promotion_order_prm_id_seq TO mochidb_owner;


--
-- Name: TABLE promotion_order; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE mochi.promotion_order TO mochi_app;


--
-- Name: SEQUENCE promotion_product_prm_id_seq; Type: ACL; Schema: mochi; Owner: postgres
--

GRANT ALL ON SEQUENCE mochi.promotion_product_prm_id_seq TO mochi_app;
GRANT ALL ON SEQUENCE mochi.promotion_product_prm_id_seq TO mochidb_owner;


--
-- Name: TABLE promotion_product; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE mochi.promotion_product TO mochi_app;


--
-- Name: SEQUENCE promotion_type_prm_typ_id_seq; Type: ACL; Schema: mochi; Owner: postgres
--

GRANT ALL ON SEQUENCE mochi.promotion_type_prm_typ_id_seq TO mochi_app;
GRANT ALL ON SEQUENCE mochi.promotion_type_prm_typ_id_seq TO mochidb_owner;


--
-- Name: TABLE promotion_type; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE mochi.promotion_type TO mochi_app;


--
-- Name: SEQUENCE rating_rat_id_seq; Type: ACL; Schema: mochi; Owner: postgres
--

GRANT ALL ON SEQUENCE mochi.rating_rat_id_seq TO mochi_app;
GRANT ALL ON SEQUENCE mochi.rating_rat_id_seq TO mochidb_owner;


--
-- Name: TABLE rating; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE mochi.rating TO mochi_app;


--
-- Name: SEQUENCE role_rle_id_seq; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT ALL ON SEQUENCE mochi.role_rle_id_seq TO mochi_app;


--
-- Name: TABLE role; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT SELECT ON TABLE mochi.role TO security_app;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE mochi.role TO mochi_app;


--
-- Name: TABLE role_type; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT SELECT ON TABLE mochi.role_type TO security_app;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE mochi.role_type TO mochi_app;


--
-- Name: SEQUENCE role_type_rle_typ_id_seq; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT ALL ON SEQUENCE mochi.role_type_rle_typ_id_seq TO mochi_app;


--
-- Name: SEQUENCE stock_on_hand_soh_id_seq; Type: ACL; Schema: mochi; Owner: postgres
--

GRANT ALL ON SEQUENCE mochi.stock_on_hand_soh_id_seq TO mochi_app;
GRANT ALL ON SEQUENCE mochi.stock_on_hand_soh_id_seq TO mochidb_owner;


--
-- Name: TABLE stock_on_hand; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE mochi.stock_on_hand TO mochi_app;


--
-- Name: SEQUENCE supplier_rle_id_seq; Type: ACL; Schema: mochi; Owner: postgres
--

GRANT ALL ON SEQUENCE mochi.supplier_rle_id_seq TO mochi_app;
GRANT ALL ON SEQUENCE mochi.supplier_rle_id_seq TO mochidb_owner;


--
-- Name: SEQUENCE supplier_sup_num_seq; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT ALL ON SEQUENCE mochi.supplier_sup_num_seq TO mochi_app;


--
-- Name: TABLE supplier; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE mochi.supplier TO mochi_app;


--
-- Name: SEQUENCE tag_tag_id_seq; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT ALL ON SEQUENCE mochi.tag_tag_id_seq TO mochi_app;


--
-- Name: TABLE tag; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE mochi.tag TO mochi_app;


--
-- Name: SEQUENCE tag_attr_lcl_tag_lcl_id_seq; Type: ACL; Schema: mochi; Owner: postgres
--

GRANT ALL ON SEQUENCE mochi.tag_attr_lcl_tag_lcl_id_seq TO mochi_app;
GRANT ALL ON SEQUENCE mochi.tag_attr_lcl_tag_lcl_id_seq TO mochidb_owner;


--
-- Name: TABLE tag_attr_lcl; Type: ACL; Schema: mochi; Owner: mochidb_owner
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE mochi.tag_attr_lcl TO mochi_app;


--
-- PostgreSQL database dump complete
--

