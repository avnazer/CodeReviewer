#ifndef __F57OK087__
#define __F57OK087__

#define DATA_VERSION_F57OK087  1
#define NID_F57OK087  _J("F57OK087")
#define STABLENAME_F57OK087  _J("F57OK087")

typedef struct
{
#define NID_EDBT  _J("EDBT")
   JCHAR           pledbt[16];          /* 0 to 31 */
#define NID_EDLN  _J("EDLN")
   MATH_NUMERIC   pledln;              /* 32 to 80 */
#define NID_EDSP  _J("EDSP")
   JCHAR           pledsp;              /* 81 to 82 */
#define NID_57EDSP  _J("57EDSP")
   JCHAR           pl57edsp;            /* 83 to 84 */
#define NID_57EXP  _J("57EXP")
   JCHAR           pl57exp;             /* 85 to 86 */
#define NID_573PLCD  _J("573PLCD")
   JCHAR           pl573plcd[4];        /* 87 to 94 */
#define NID_MCU  _J("MCU")
   JCHAR           plmcu[13];           /* 95 to 120 */
#define NID_57SRCT  _J("57SRCT")
   JCHAR           pl57srct;            /* 121 to 122 */
#define NID_LOCN  _J("LOCN")
   JCHAR           pllocn[21];          /* 123 to 164 */
#define NID_57WHFLG  _J("57WHFLG")
   JCHAR           pl57whflg;           /* 165 to 166 */
#define NID_ORTP  _J("ORTP")
   JCHAR           plortp[9];           /* 167 to 184 */
#define NID_57LOTN  _J("57LOTN")
   JCHAR           pl57lotn[31];        /* 185 to 246 */
#define NID_ITM  _J("ITM")
   MATH_NUMERIC   plitm;               /* 247 to 295 */
#define NID_LITM  _J("LITM")
   JCHAR           pllitm[26];          /* 296 to 347 */
#define NID_DSC1  _J("DSC1")
   JCHAR           pldsc1[31];          /* 348 to 409 */
#define NID_LOTN  _J("LOTN")
   JCHAR           pllotn[31];          /* 410 to 471 */
#define NID_MMEJ  _J("MMEJ")
   JDEDATE        plmmej;              /* 472 to 477 */
#define NID_UORG  _J("UORG")
   MATH_NUMERIC   pluorg;              /* 478 to 526 */
#define NID_UOM  _J("UOM")
   JCHAR           pluom[3];            /* 527 to 532 */
#define NID_QTYU  _J("QTYU")
   MATH_NUMERIC   plqtyu;              /* 533 to 581 */
#define NID_UOM1  _J("UOM1")
   JCHAR           pluom1[3];           /* 582 to 587 */
#define NID_URRF  _J("URRF")
   JCHAR           plurrf[16];          /* 588 to 619 */
#define NID_57IMPFLG  _J("57IMPFLG")
   JCHAR           pl57impflg;          /* 620 to 621 */
#define NID_GLPT  _J("GLPT")
   JCHAR           plglpt[5];           /* 622 to 631 */
#define NID_SRP2  _J("SRP2")
   JCHAR           plsrp2[4];           /* 632 to 639 */
#define NID_PRP1  _J("PRP1")
   JCHAR           plprp1[4];           /* 640 to 647 */
#define NID_CRTJ  _J("CRTJ")
   JDEDATE        plcrtj;              /* 648 to 653 */
#define NID_CRTT  _J("CRTT")
   MATH_NUMERIC   plcrtt;              /* 654 to 702 */
#define NID_TORG  _J("TORG")
   JCHAR           pltorg[11];          /* 703 to 724 */
#define NID_PID  _J("PID")
   JCHAR           plpid[11];           /* 725 to 746 */
#define NID_VERS  _J("VERS")
   JCHAR           plvers[11];          /* 747 to 768 */
#define NID_JOBN  _J("JOBN")
   JCHAR           pljobn[11];          /* 769 to 790 */
#define NID_USER  _J("USER")
   JCHAR           pluser[11];          /* 791 to 812 */
#define NID_UPMJ  _J("UPMJ")
   JDEDATE        plupmj;              /* 813 to 818 */
#define NID_UPMT  _J("UPMT")
   MATH_NUMERIC   plupmt;              /* 819 to 867 */
#define NID_DTAI  _J("DTAI")
   JCHAR           pldtai[11];          /* 868 to 889 */
} F57OK087, *LPF57OK087;

/* PRIMARY INDEX */
/* EDBT, EDLN */
#define ID_F57OK087_EDBT__EDLN  1L
typedef struct
{
   JCHAR           pledbt[16];          /* 0 to 31 */
   MATH_NUMERIC   pledln;              /* 32 to 80 */
} KEY1_F57OK087, *LPKEY1_F57OK087;

/* CRTJ DESC */
#define ID_F57OK087_CRTJ_DESC  2L
typedef struct
{
   JDEDATE        plcrtj;              /* 81 to 86 */
} KEY2_F57OK087, *LPKEY2_F57OK087;

/* EDBT,ORTP,57LOTN,LOCN,LITM ..+ */
#define ID_F57OK087_EDBT_ORTP_57LOTN_LOCN_LITM  3L
typedef struct
{
   JCHAR           pledbt[16];          /* 87 to 118 */
   JCHAR           plortp[9];           /* 119 to 136 */
   JCHAR           pl57lotn[31];        /* 137 to 198 */
   JCHAR           pllocn[21];          /* 199 to 240 */
   JCHAR           pllitm[26];          /* 241 to 292 */
   JCHAR           pllotn[31];          /* 293 to 354 */
} KEY3_F57OK087, *LPKEY3_F57OK087;

/* EDBT,ITM,LOTN */
#define ID_F57OK087_EDBT_ITM_LOTN  4L
typedef struct
{
   JCHAR           pledbt[16];          /* 355 to 386 */
   MATH_NUMERIC   plitm;               /* 387 to 435 */
   JCHAR           pllotn[31];          /* 436 to 497 */
} KEY4_F57OK087, *LPKEY4_F57OK087;

#endif
