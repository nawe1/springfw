SELECT
        TO_CHAR(CREATE_DATE,'YYYY-MM-DD')
  FROM
        BOARD;
        
        
        
SELECT
        LENGTH(BOARD_NO)
  FROM
        BOARD;
        
SELECT
        COUNT(BOARD_NO)
  FROM
        BOARD;
        
SELECT 
        *
  FROM
        BOARD;






alter table board modify (change_name varchar2(200));
commit;


 --INLINEVIEW
SELECT
        BOARD_NO,
        BOARD_TITLE,
        BOARD_WRITER,
        COUNT,
        CREATE_DATE,
        ORIGIN_NAME,
        RNUM
FROM
        (SELECT
                BOARD_NO,
                BOARD_TITLE,
                BOARD_WRITER,
                COUNT,
                CREATE_DATE,
                ORIGIN_NAME,
                ROWNUM RNUM
        FROM
            (SELECT
                 BOARD_NO,
                 BOARD_TITLE,
                 BOARD_WRITER,
                 COUNT,
                 CREATE_DATE,
                 ORIGIN_NAME,
                 ROWNUM RNUM
            FROM
                  BOARD
           WHERE
                  STATUS ='Y'
            ORDER
               BY  
                   BOARD_NO DESC))
  WHERE
            RNUM BETWEEN 11 AND 16;
        
        
        SELECT
				BOARD_NO,
				BOARD_TITLE,
				BOARD_CONTENT,
				BOARD_WRITER,
				COUNT,
				ORIGIN_NAME,
				CHANGE_NANE,
				REPLY_NO,
				REPLY_CONTENT,
				REPLY,WRITER,
				REF_BNO,
				BOARD.CREATE_DATE B_CREATE_DATE,
				REPLY.CREATE_DATE R_CREATE_DATE
		  FROM
				BOARD
		  LEFT
		  JOIN
				REPLY ON(BOARD_NO = REF_BNO)
		 WHERE
				BOARD_NO ={boardNo}
	