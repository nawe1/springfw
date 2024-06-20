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
        