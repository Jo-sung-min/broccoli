CREATE TABLE `tb_sso_user_sync` (
    `USER_ID` varchar(15) NOT NULL COMMENT '사용자_ID',
    `USER_NM` varchar(60) DEFAULT NULL COMMENT '사용자_명',
    `USER_CLS_CD` char(1) DEFAULT NULL COMMENT '사용자_유형',
    `SNS_ID` varchar(200) DEFAULT NULL COMMENT 'SNS_ID',
    `SNS_SITE_CD` char(2) DEFAULT NULL COMMENT 'SNS_사이트_코드',
    `ENT_DTM` char(14) DEFAULT NULL COMMENT '가입_일시',
    `PWD_CHNG_DT` char(8) DEFAULT NULL COMMENT '비밀번호_변경_일자',
    `BRDY` char(8) DEFAULT NULL COMMENT '생년월일',
    `SEX_CD` char(1) DEFAULT NULL COMMENT '성별_코드',
    `EML_ADDR` varchar(100) DEFAULT NULL COMMENT '이메일_주소',
    `CRY_TLNO` varchar(15) DEFAULT NULL COMMENT '휴대_전화번호',
    `CRTN_DTM` char(14) DEFAULT NULL COMMENT '생성_일시',
    `LST_CHNG_DTM` char(14) DEFAULT NULL COMMENT '최종_변경_일시',
    PRIMARY KEY (`USER_ID`),
    KEY `IX_SSO_USER_SYNC_01` (`USER_NM`),
    KEY `IX_SSO_USER_SYNC_02` (`CRY_TLNO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='사용자_등기화';
