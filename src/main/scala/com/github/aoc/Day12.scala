package com.github.aoc

import scala.collection.mutable.ListBuffer
import scala.collection.mutable.HashMap

object Day12 {
  
  val ex = """0 <-> 2
1 <-> 1
2 <-> 0, 3, 4
3 <-> 2, 4
4 <-> 2, 3, 6
5 <-> 6
6 <-> 4, 5"""
  
  val data="""0 <-> 396, 1867
1 <-> 1749
2 <-> 466, 675, 1661
3 <-> 3, 328, 1160
4 <-> 4, 953
5 <-> 1922
6 <-> 1273
7 <-> 7, 959
8 <-> 96, 274
9 <-> 9, 1036
10 <-> 1783
11 <-> 879
12 <-> 382, 1390
13 <-> 1214, 1473
14 <-> 434, 508, 1470
15 <-> 324, 747
16 <-> 195, 894
17 <-> 536
18 <-> 1024, 1198
19 <-> 1616
20 <-> 797
21 <-> 885
22 <-> 1001
23 <-> 569, 1496
24 <-> 392
25 <-> 32
26 <-> 820
27 <-> 655
28 <-> 1044
29 <-> 307, 675, 1518
30 <-> 1547, 1775, 1885
31 <-> 237
32 <-> 25, 1736
33 <-> 390
34 <-> 279, 1423
35 <-> 1367, 1761
36 <-> 154, 353, 1047
37 <-> 1512
38 <-> 1618, 1665
39 <-> 1004
40 <-> 276, 335
41 <-> 315
42 <-> 340, 901, 954
43 <-> 415, 652
44 <-> 550
45 <-> 145, 1352
46 <-> 1653
47 <-> 711
48 <-> 274, 1155
49 <-> 1960
50 <-> 263, 372, 395
51 <-> 51, 327, 1530
52 <-> 1506, 1875
53 <-> 53, 213, 1872
54 <-> 270
55 <-> 997
56 <-> 1649
57 <-> 789
58 <-> 98, 826, 1548
59 <-> 1918
60 <-> 447
61 <-> 448
62 <-> 966, 1152, 1895
63 <-> 883
64 <-> 1320, 1656
65 <-> 417, 1263
66 <-> 750, 1708
67 <-> 1198, 1324, 1929
68 <-> 1932
69 <-> 765
70 <-> 221, 624, 1745
71 <-> 599, 1120
72 <-> 106, 485, 986
73 <-> 765, 1008, 1822
74 <-> 464
75 <-> 1856
76 <-> 798, 1575
77 <-> 122, 706, 1720
78 <-> 236, 583, 1505
79 <-> 711, 1480, 1809
80 <-> 1379, 1705
81 <-> 315, 984, 1440
82 <-> 758
83 <-> 779, 1768
84 <-> 100, 1427
85 <-> 178
86 <-> 490
87 <-> 982, 1978
88 <-> 1329, 1485, 1845
89 <-> 327, 963
90 <-> 1740
91 <-> 242
92 <-> 351, 1290
93 <-> 172, 1052
94 <-> 1333, 1598, 1856
95 <-> 567
96 <-> 8, 732
97 <-> 97
98 <-> 58, 1219, 1330
99 <-> 231, 544, 848, 1923
100 <-> 84
101 <-> 1438
102 <-> 705
103 <-> 508, 677
104 <-> 104
105 <-> 519
106 <-> 72
107 <-> 107, 1169
108 <-> 277
109 <-> 1746
110 <-> 686
111 <-> 115, 903, 915
112 <-> 493
113 <-> 113
114 <-> 1217, 1437
115 <-> 111
116 <-> 535, 1701
117 <-> 117, 910
118 <-> 192, 982
119 <-> 119, 1274
120 <-> 1800
121 <-> 970
122 <-> 77, 1208
123 <-> 1073, 1984
124 <-> 530, 600, 1254
125 <-> 125
126 <-> 166
127 <-> 563, 607, 1389
128 <-> 260, 1160
129 <-> 683, 977, 1366
130 <-> 981
131 <-> 1113, 1209, 1461
132 <-> 411
133 <-> 794, 1478
134 <-> 134, 419
135 <-> 755
136 <-> 507
137 <-> 855
138 <-> 1434
139 <-> 1623, 1989
140 <-> 796, 1133, 1414
141 <-> 476
142 <-> 385, 1708
143 <-> 932, 1616
144 <-> 1014
145 <-> 45
146 <-> 146, 369, 489
147 <-> 1901
148 <-> 676
149 <-> 188, 377
150 <-> 318, 1128
151 <-> 549, 1478
152 <-> 641, 1090
153 <-> 1770
154 <-> 36, 585
155 <-> 1450
156 <-> 197, 1764
157 <-> 157
158 <-> 158
159 <-> 1951
160 <-> 1420, 1782
161 <-> 1587, 1990
162 <-> 1158, 1927
163 <-> 372, 1653, 1796
164 <-> 1916
165 <-> 1653, 1864
166 <-> 126, 166, 1497
167 <-> 167, 679
168 <-> 308, 1176
169 <-> 378, 475, 921, 1057
170 <-> 651, 1175
171 <-> 1275
172 <-> 93, 172
173 <-> 1763
174 <-> 174, 1318
175 <-> 243, 866, 1053, 1495
176 <-> 1212
177 <-> 949
178 <-> 85, 882
179 <-> 1427, 1704
180 <-> 1802
181 <-> 1362
182 <-> 764, 793
183 <-> 1076
184 <-> 184, 969, 1250, 1581
185 <-> 227, 833, 1835
186 <-> 568, 1378
187 <-> 1142
188 <-> 149
189 <-> 873
190 <-> 1650
191 <-> 1101, 1131, 1313
192 <-> 118, 244, 278, 1025, 1232, 1554
193 <-> 193, 570
194 <-> 772, 1595
195 <-> 16
196 <-> 1173
197 <-> 156, 925, 1880
198 <-> 320, 1489, 1675
199 <-> 199
200 <-> 342, 875, 1787
201 <-> 415
202 <-> 1472, 1846
203 <-> 360, 1187
204 <-> 558
205 <-> 1026
206 <-> 795, 1405
207 <-> 207, 658
208 <-> 780, 1587
209 <-> 661
210 <-> 367, 1620
211 <-> 1473, 1783
212 <-> 656
213 <-> 53, 724, 1017
214 <-> 1282, 1735
215 <-> 1564
216 <-> 1784
217 <-> 824
218 <-> 218, 1238
219 <-> 675, 1096
220 <-> 1154
221 <-> 70, 1743
222 <-> 922, 1268
223 <-> 1646
224 <-> 850
225 <-> 1072
226 <-> 582
227 <-> 185, 499
228 <-> 465
229 <-> 712, 1767
230 <-> 1259, 1916
231 <-> 99
232 <-> 1024, 1850
233 <-> 1443
234 <-> 397, 708, 1296
235 <-> 967, 1179
236 <-> 78, 1380, 1826
237 <-> 31, 287, 659
238 <-> 238
239 <-> 557
240 <-> 1753
241 <-> 363, 731
242 <-> 91, 1713
243 <-> 175
244 <-> 192, 721, 820, 1277, 1785
245 <-> 961
246 <-> 1509, 1986
247 <-> 1781
248 <-> 1381
249 <-> 879
250 <-> 391, 1268, 1799
251 <-> 326, 771
252 <-> 806, 1371, 1818
253 <-> 846
254 <-> 1414
255 <-> 1098, 1379, 1724
256 <-> 1078
257 <-> 705, 1078
258 <-> 1264, 1865
259 <-> 524, 1140
260 <-> 128, 1270, 1559
261 <-> 1125, 1306, 1541
262 <-> 1199, 1981
263 <-> 50
264 <-> 264
265 <-> 1123, 1453
266 <-> 774, 833, 1694, 1797
267 <-> 1562, 1574
268 <-> 425, 1611
269 <-> 595, 851, 1873
270 <-> 54, 1410, 1828
271 <-> 325, 1866
272 <-> 294
273 <-> 480
274 <-> 8, 48, 1012
275 <-> 1299
276 <-> 40, 1767
277 <-> 108, 1539
278 <-> 192, 1660
279 <-> 34, 1157, 1375
280 <-> 1654
281 <-> 627
282 <-> 288, 340
283 <-> 1323, 1618
284 <-> 284
285 <-> 1110
286 <-> 550, 756, 863, 1735
287 <-> 237, 1615, 1847
288 <-> 282, 1347, 1782
289 <-> 720, 1006, 1260
290 <-> 1272
291 <-> 1891
292 <-> 292, 1215, 1549
293 <-> 825
294 <-> 272, 484, 1789
295 <-> 818
296 <-> 974, 1870
297 <-> 551, 859
298 <-> 298, 1087
299 <-> 299, 1946
300 <-> 300, 1002, 1029, 1848
301 <-> 733, 1768
302 <-> 1156
303 <-> 1366
304 <-> 1632
305 <-> 1610
306 <-> 1292
307 <-> 29
308 <-> 168, 822
309 <-> 309, 636, 997
310 <-> 344, 836
311 <-> 311, 471
312 <-> 1498
313 <-> 738, 1221
314 <-> 1018
315 <-> 41, 81, 935, 1552
316 <-> 325, 1189
317 <-> 595
318 <-> 150, 481, 1859, 1949
319 <-> 1912
320 <-> 198, 1046, 1311
321 <-> 824, 1590
322 <-> 393
323 <-> 634
324 <-> 15, 1385
325 <-> 271, 316, 1090
326 <-> 251, 503
327 <-> 51, 89, 1186, 1617, 1696, 1869
328 <-> 3, 665
329 <-> 544, 1816
330 <-> 425
331 <-> 1214
332 <-> 332, 418, 758
333 <-> 333
334 <-> 1536
335 <-> 40, 963
336 <-> 359, 690, 1613
337 <-> 667, 1004, 1532, 1900
338 <-> 338, 1970
339 <-> 1077
340 <-> 42, 282, 1421
341 <-> 341, 616
342 <-> 200, 1514
343 <-> 368, 1273, 1570
344 <-> 310, 473
345 <-> 1031
346 <-> 1455
347 <-> 1701
348 <-> 729, 1342
349 <-> 1517
350 <-> 1918
351 <-> 92, 1235
352 <-> 1406
353 <-> 36
354 <-> 744
355 <-> 494
356 <-> 424, 1960
357 <-> 1827
358 <-> 612, 781, 1245, 1303, 1455
359 <-> 336, 993, 1193, 1693
360 <-> 203, 1937
361 <-> 1791, 1795
362 <-> 873
363 <-> 241, 1802
364 <-> 670
365 <-> 959, 1085, 1432
366 <-> 689
367 <-> 210, 1040
368 <-> 343, 716, 1382
369 <-> 146, 1013, 1305
370 <-> 948, 1935
371 <-> 1612, 1782
372 <-> 50, 163
373 <-> 373, 495
374 <-> 1127
375 <-> 913, 1844
376 <-> 620, 982, 1103
377 <-> 149, 1247, 1251
378 <-> 169
379 <-> 1902
380 <-> 701, 1654
381 <-> 381
382 <-> 12, 526, 1123, 1868
383 <-> 391, 1458
384 <-> 384
385 <-> 142, 989, 1442, 1511
386 <-> 588, 1437, 1532
387 <-> 1917
388 <-> 1738
389 <-> 629, 1511
390 <-> 33, 390
391 <-> 250, 383
392 <-> 24, 701, 1726
393 <-> 322, 450, 1900, 1962
394 <-> 394
395 <-> 50
396 <-> 0, 464, 563
397 <-> 234, 401, 1599
398 <-> 398
399 <-> 399, 1452, 1966
400 <-> 474, 903
401 <-> 397, 729
402 <-> 922
403 <-> 1544, 1756
404 <-> 1192, 1634
405 <-> 1602
406 <-> 675
407 <-> 1670
408 <-> 477
409 <-> 1442
410 <-> 1087
411 <-> 132, 705, 1741
412 <-> 938, 1134
413 <-> 530
414 <-> 1346
415 <-> 43, 201, 1381
416 <-> 1122, 1730
417 <-> 65, 417
418 <-> 332
419 <-> 134, 1044, 1592
420 <-> 651, 1466
421 <-> 882, 1295
422 <-> 486
423 <-> 1870
424 <-> 356
425 <-> 268, 330, 575
426 <-> 1850
427 <-> 427
428 <-> 497, 594, 1295, 1863
429 <-> 1263
430 <-> 1525
431 <-> 1111
432 <-> 599, 1050
433 <-> 987, 1279
434 <-> 14, 434
435 <-> 785, 1381
436 <-> 459, 1141, 1618
437 <-> 944
438 <-> 1674, 1928, 1945
439 <-> 1638
440 <-> 622
441 <-> 1648
442 <-> 1099
443 <-> 815, 967
444 <-> 484, 859, 1446, 1717
445 <-> 649, 799, 957, 1185, 1412
446 <-> 639, 1952
447 <-> 60, 743
448 <-> 61, 783, 1085
449 <-> 539, 1257, 1319
450 <-> 393, 1167
451 <-> 1918
452 <-> 1579
453 <-> 821, 1886
454 <-> 1075, 1633, 1723
455 <-> 455
456 <-> 959
457 <-> 457, 1871
458 <-> 458
459 <-> 436
460 <-> 722, 1273
461 <-> 878, 1093
462 <-> 546
463 <-> 809
464 <-> 74, 396
465 <-> 228, 577, 1483
466 <-> 2, 769
467 <-> 1304, 1368, 1531
468 <-> 835, 883, 1071
469 <-> 540, 747, 1137, 1339
470 <-> 1649, 1722
471 <-> 311, 874, 952, 1239
472 <-> 472, 1811
473 <-> 344
474 <-> 400
475 <-> 169, 889, 1738
476 <-> 141, 1566
477 <-> 408, 1611
478 <-> 629, 733
479 <-> 688
480 <-> 273, 859
481 <-> 318
482 <-> 1069
483 <-> 1609
484 <-> 294, 444
485 <-> 72, 1838
486 <-> 422, 662, 1011, 1026
487 <-> 1195
488 <-> 990, 1068
489 <-> 146
490 <-> 86, 958
491 <-> 806, 890, 1734
492 <-> 548, 1233, 1302
493 <-> 112, 1899, 1942
494 <-> 355, 494, 650
495 <-> 373, 1040, 1372, 1691
496 <-> 749
497 <-> 428
498 <-> 1800
499 <-> 227
500 <-> 1153, 1320, 1438, 1853
501 <-> 1038, 1744
502 <-> 1949
503 <-> 326, 1578
504 <-> 504
505 <-> 1672
506 <-> 506, 1149
507 <-> 136, 1454
508 <-> 14, 103, 1010, 1796
509 <-> 1376, 1838
510 <-> 1301
511 <-> 652, 842
512 <-> 512, 1102
513 <-> 789
514 <-> 1605
515 <-> 914
516 <-> 516, 1091
517 <-> 1513, 1981
518 <-> 1787
519 <-> 105, 519, 828
520 <-> 700, 1924
521 <-> 692, 1522, 1605
522 <-> 1537
523 <-> 523, 1300
524 <-> 259, 1410
525 <-> 1749, 1821
526 <-> 382
527 <-> 1174, 1571
528 <-> 942, 1429
529 <-> 768, 1177, 1820
530 <-> 124, 413, 826, 1467, 1882
531 <-> 621, 1940
532 <-> 712, 807
533 <-> 578, 1655
534 <-> 854
535 <-> 116
536 <-> 17, 929, 1482
537 <-> 537, 1265
538 <-> 1461
539 <-> 449, 1805
540 <-> 469, 1035
541 <-> 730
542 <-> 1147, 1244
543 <-> 543, 696, 1877
544 <-> 99, 329
545 <-> 545, 1936
546 <-> 462, 711, 1957
547 <-> 950, 1510
548 <-> 492, 730
549 <-> 151, 1054, 1297
550 <-> 44, 286, 1956
551 <-> 297, 1117, 1431, 1739
552 <-> 648, 772
553 <-> 1709
554 <-> 951, 1361
555 <-> 555, 1772
556 <-> 802, 1643
557 <-> 239, 557, 1142, 1893
558 <-> 204, 634
559 <-> 1792
560 <-> 612, 1693
561 <-> 561, 1007
562 <-> 809
563 <-> 127, 396, 1396
564 <-> 564, 1658
565 <-> 932, 1491
566 <-> 863, 1563, 1839
567 <-> 95, 716, 1514
568 <-> 186
569 <-> 23, 1261, 1378
570 <-> 193
571 <-> 1768
572 <-> 572, 1667
573 <-> 1977
574 <-> 835
575 <-> 425, 1039
576 <-> 1124
577 <-> 465, 732, 1110, 1625
578 <-> 533, 1876
579 <-> 946
580 <-> 580
581 <-> 837
582 <-> 226, 1738
583 <-> 78, 1526
584 <-> 847, 1106
585 <-> 154, 1723
586 <-> 1391
587 <-> 1589, 1725
588 <-> 386, 588, 991
589 <-> 589, 645
590 <-> 1296, 1972
591 <-> 591, 1556
592 <-> 1070, 1970
593 <-> 1786
594 <-> 428
595 <-> 269, 317, 619, 1638
596 <-> 1973
597 <-> 597, 750, 1109
598 <-> 1530, 1887
599 <-> 71, 432, 1904
600 <-> 124, 603, 1205
601 <-> 1694
602 <-> 1037, 1094, 1351
603 <-> 600, 689
604 <-> 667, 1143, 1460
605 <-> 643
606 <-> 953
607 <-> 127
608 <-> 836, 1000
609 <-> 609, 748, 1030
610 <-> 1879
611 <-> 1674
612 <-> 358, 560, 1918
613 <-> 784
614 <-> 1214
615 <-> 1643
616 <-> 341, 917, 1356, 1854
617 <-> 685
618 <-> 1297
619 <-> 595
620 <-> 376
621 <-> 531, 1054, 1236
622 <-> 440, 926, 992, 1080
623 <-> 1268
624 <-> 70
625 <-> 1251
626 <-> 1341
627 <-> 281, 627, 811, 1523
628 <-> 1060, 1911
629 <-> 389, 478
630 <-> 1401
631 <-> 665, 789
632 <-> 632, 654
633 <-> 797, 1402
634 <-> 323, 558, 1560
635 <-> 800, 1991
636 <-> 309, 1317
637 <-> 1803
638 <-> 1091
639 <-> 446, 1778
640 <-> 1131
641 <-> 152, 1116
642 <-> 1690
643 <-> 605, 1604
644 <-> 874, 1471
645 <-> 589, 1500
646 <-> 1517
647 <-> 1394, 1750
648 <-> 552, 838, 1807
649 <-> 445, 734
650 <-> 494, 1276
651 <-> 170, 420, 1469
652 <-> 43, 511, 1823
653 <-> 1442, 1558
654 <-> 632
655 <-> 27, 1058, 1239
656 <-> 212, 992, 1765
657 <-> 946
658 <-> 207
659 <-> 237, 659
660 <-> 831, 1162, 1540
661 <-> 209, 1588
662 <-> 486, 924
663 <-> 1172, 1650
664 <-> 944
665 <-> 328, 631, 1626
666 <-> 1350, 1714, 1979
667 <-> 337, 604
668 <-> 1067, 1983
669 <-> 669
670 <-> 364, 764, 799
671 <-> 888, 1063, 1139, 1310, 1570
672 <-> 1779
673 <-> 903
674 <-> 870
675 <-> 2, 29, 219, 406, 881
676 <-> 148, 1228
677 <-> 103, 717
678 <-> 1094
679 <-> 167, 1679
680 <-> 1605
681 <-> 1111
682 <-> 1385
683 <-> 129
684 <-> 1105
685 <-> 617, 1843
686 <-> 110, 1099, 1339
687 <-> 736
688 <-> 479, 923, 1597
689 <-> 366, 603, 1083, 1258, 1407
690 <-> 336, 1182
691 <-> 1585, 1922
692 <-> 521
693 <-> 782, 908, 1790
694 <-> 1935, 1939
695 <-> 1696
696 <-> 543, 1908
697 <-> 1039
698 <-> 1736
699 <-> 1186
700 <-> 520, 735, 1814, 1990
701 <-> 380, 392
702 <-> 1399
703 <-> 1300
704 <-> 704
705 <-> 102, 257, 411
706 <-> 77, 1016, 1561, 1701
707 <-> 1566
708 <-> 234
709 <-> 709
710 <-> 1731
711 <-> 47, 79, 546, 973
712 <-> 229, 532, 1779
713 <-> 724
714 <-> 1905, 1953
715 <-> 865, 1703
716 <-> 368, 567, 760, 1758
717 <-> 677, 727, 1340
718 <-> 1129
719 <-> 1042
720 <-> 289
721 <-> 244
722 <-> 460, 1525
723 <-> 723, 1563
724 <-> 213, 713
725 <-> 1955
726 <-> 1085
727 <-> 717, 1485
728 <-> 1940
729 <-> 348, 401, 1062
730 <-> 541, 548
731 <-> 241, 731, 1388
732 <-> 96, 577, 970, 1968
733 <-> 301, 478
734 <-> 649, 1380, 1388
735 <-> 700
736 <-> 687, 1861
737 <-> 972
738 <-> 313
739 <-> 947
740 <-> 1835
741 <-> 1153, 1528
742 <-> 786
743 <-> 447, 948, 1542, 1601
744 <-> 354, 1643
745 <-> 1213
746 <-> 746, 919, 1607
747 <-> 15, 469, 768, 905
748 <-> 609, 805, 1279
749 <-> 496, 1588, 1824
750 <-> 66, 597
751 <-> 946
752 <-> 1521
753 <-> 885, 1394
754 <-> 1413
755 <-> 135, 1532, 1801, 1833
756 <-> 286, 1938
757 <-> 1315
758 <-> 82, 332, 1721
759 <-> 1168
760 <-> 716, 1557
761 <-> 921
762 <-> 1172
763 <-> 1469
764 <-> 182, 670, 1955
765 <-> 69, 73
766 <-> 1298
767 <-> 767
768 <-> 529, 747
769 <-> 466, 1747, 1909
770 <-> 770, 1284
771 <-> 251, 1520, 1979
772 <-> 194, 552, 1397
773 <-> 1105, 1827
774 <-> 266
775 <-> 1315
776 <-> 800, 1919
777 <-> 846, 1019
778 <-> 1914
779 <-> 83, 1818
780 <-> 208, 1417
781 <-> 358, 943, 1247
782 <-> 693, 1732
783 <-> 448, 1977
784 <-> 613, 1408
785 <-> 435
786 <-> 742, 919, 1252
787 <-> 787, 992
788 <-> 844, 1845
789 <-> 57, 513, 631
790 <-> 1609, 1680, 1912
791 <-> 854, 1985
792 <-> 1296
793 <-> 182, 1857
794 <-> 133
795 <-> 206, 1413, 1632, 1929
796 <-> 140, 1700, 1789, 1804
797 <-> 20, 633, 1396
798 <-> 76, 1862
799 <-> 445, 670
800 <-> 635, 776
801 <-> 1027, 1518
802 <-> 556, 1018, 1539
803 <-> 803, 1287, 1926
804 <-> 804, 1697, 1713
805 <-> 748
806 <-> 252, 491
807 <-> 532, 942
808 <-> 1633
809 <-> 463, 562, 1163
810 <-> 817, 1498
811 <-> 627, 1888
812 <-> 1222, 1538
813 <-> 978
814 <-> 979
815 <-> 443, 1254, 1515
816 <-> 1409
817 <-> 810
818 <-> 295, 818
819 <-> 1117
820 <-> 26, 244, 820
821 <-> 453
822 <-> 308, 826
823 <-> 1824
824 <-> 217, 321, 1370
825 <-> 293, 1834
826 <-> 58, 530, 822, 1077, 1106, 1652
827 <-> 1535
828 <-> 519
829 <-> 1311, 1472
830 <-> 1836
831 <-> 660
832 <-> 1504, 1766
833 <-> 185, 266
834 <-> 834
835 <-> 468, 574
836 <-> 310, 608, 1383
837 <-> 581, 968
838 <-> 648, 1089, 1409
839 <-> 1546, 1793
840 <-> 840, 1014
841 <-> 918
842 <-> 511, 1269, 1752
843 <-> 1437
844 <-> 788, 1996
845 <-> 943
846 <-> 253, 777, 1839
847 <-> 584
848 <-> 99, 892, 1392, 1629
849 <-> 849, 1258
850 <-> 224, 1408, 1988
851 <-> 269, 851
852 <-> 1405
853 <-> 1099, 1798
854 <-> 534, 791, 1248
855 <-> 137, 1468, 1918
856 <-> 1281
857 <-> 938, 1060
858 <-> 1498
859 <-> 297, 444, 480, 1272, 1695
860 <-> 1352, 1506
861 <-> 1569, 1950
862 <-> 1895
863 <-> 286, 566
864 <-> 1289, 1737
865 <-> 715
866 <-> 175, 1988
867 <-> 1830
868 <-> 1467
869 <-> 869
870 <-> 674, 1524
871 <-> 1862
872 <-> 1726
873 <-> 189, 362, 936, 971, 1356
874 <-> 471, 644, 1322
875 <-> 200
876 <-> 1075, 1154, 1196
877 <-> 1434
878 <-> 461
879 <-> 11, 249, 1638
880 <-> 1014, 1221
881 <-> 675, 1098, 1576, 1942
882 <-> 178, 421, 882
883 <-> 63, 468
884 <-> 884
885 <-> 21, 753, 947
886 <-> 1043
887 <-> 1436, 1533, 1624, 1788
888 <-> 671, 1020
889 <-> 475
890 <-> 491
891 <-> 1709
892 <-> 848, 892
893 <-> 1914
894 <-> 16, 924, 1434
895 <-> 1363
896 <-> 1301, 1393
897 <-> 897
898 <-> 1074, 1539
899 <-> 1656
900 <-> 900, 1433, 1635, 1980
901 <-> 42
902 <-> 977
903 <-> 111, 400, 673, 1512, 1965
904 <-> 1091, 1808
905 <-> 747
906 <-> 1160, 1401, 1703
907 <-> 1223, 1666
908 <-> 693, 1117
909 <-> 1722
910 <-> 117, 1023
911 <-> 945
912 <-> 1600
913 <-> 375, 1784
914 <-> 515, 1108, 1362, 1808
915 <-> 111, 1535
916 <-> 1106
917 <-> 616
918 <-> 841, 918, 1331
919 <-> 746, 786, 1711
920 <-> 966, 1028, 1545
921 <-> 169, 761, 1669
922 <-> 222, 402, 1562
923 <-> 688
924 <-> 662, 894
925 <-> 197, 1550
926 <-> 622
927 <-> 927
928 <-> 1723
929 <-> 536, 1235
930 <-> 1409, 1566
931 <-> 1189
932 <-> 143, 565
933 <-> 1159, 1947
934 <-> 1986
935 <-> 315
936 <-> 873, 1604
937 <-> 937
938 <-> 412, 857, 1247
939 <-> 1087
940 <-> 1078
941 <-> 1350
942 <-> 528, 807, 1813
943 <-> 781, 845
944 <-> 437, 664, 1045, 1488
945 <-> 911, 979, 1913
946 <-> 579, 657, 751, 946, 1071
947 <-> 739, 885, 1685
948 <-> 370, 743, 1844
949 <-> 177, 1312, 1491
950 <-> 547, 1248, 1418
951 <-> 554, 1044, 1062
952 <-> 471
953 <-> 4, 606
954 <-> 42
955 <-> 1136
956 <-> 1204
957 <-> 445
958 <-> 490, 1994
959 <-> 7, 365, 456
960 <-> 960
961 <-> 245, 1900
962 <-> 1594
963 <-> 89, 335
964 <-> 1090
965 <-> 965
966 <-> 62, 920, 1242
967 <-> 235, 443, 1286
968 <-> 837, 968, 1229, 1866
969 <-> 184, 1451
970 <-> 121, 732, 978
971 <-> 873
972 <-> 737, 1487, 1969
973 <-> 711
974 <-> 296
975 <-> 1831
976 <-> 1212, 1381
977 <-> 129, 902, 1044
978 <-> 813, 970, 1789
979 <-> 814, 945
980 <-> 980
981 <-> 130, 1684
982 <-> 87, 118, 376
983 <-> 1214, 1543
984 <-> 81, 1825
985 <-> 985, 1527
986 <-> 72, 1476
987 <-> 433, 1393, 1958
988 <-> 988
989 <-> 385
990 <-> 488
991 <-> 588, 1105, 1145, 1201
992 <-> 622, 656, 787
993 <-> 359
994 <-> 1164, 1253
995 <-> 1933
996 <-> 1742, 1764
997 <-> 55, 309
998 <-> 1838
999 <-> 1302, 1597
1000 <-> 608, 1687, 1974
1001 <-> 22, 1149, 1965
1002 <-> 300
1003 <-> 1891, 1920
1004 <-> 39, 337
1005 <-> 1544, 1747, 1805
1006 <-> 289, 1374, 1636
1007 <-> 561
1008 <-> 73, 1221
1009 <-> 1009
1010 <-> 508
1011 <-> 486, 1228
1012 <-> 274
1013 <-> 369, 1778
1014 <-> 144, 840, 880
1015 <-> 1240, 1352, 1665
1016 <-> 706
1017 <-> 213
1018 <-> 314, 802
1019 <-> 777
1020 <-> 888, 1718
1021 <-> 1981
1022 <-> 1381, 1564, 1843
1023 <-> 910
1024 <-> 18, 232
1025 <-> 192
1026 <-> 205, 486
1027 <-> 801, 1652
1028 <-> 920
1029 <-> 300
1030 <-> 609
1031 <-> 345, 1079, 1596, 1964
1032 <-> 1918
1033 <-> 1550
1034 <-> 1034
1035 <-> 540
1036 <-> 9, 1959
1037 <-> 602, 1178
1038 <-> 501
1039 <-> 575, 697, 1311
1040 <-> 367, 495
1041 <-> 1041
1042 <-> 719, 1567, 1982
1043 <-> 886, 1778
1044 <-> 28, 419, 951, 977
1045 <-> 944
1046 <-> 320
1047 <-> 36
1048 <-> 1495, 1508, 1919
1049 <-> 1049, 1960
1050 <-> 432
1051 <-> 1793
1052 <-> 93, 1454
1053 <-> 175, 1744, 1881
1054 <-> 549, 621, 1054, 1119, 1809
1055 <-> 1333
1056 <-> 1109
1057 <-> 169
1058 <-> 655
1059 <-> 1434, 1444
1060 <-> 628, 857
1061 <-> 1560
1062 <-> 729, 951
1063 <-> 671, 1549
1064 <-> 1759, 1904
1065 <-> 1541
1066 <-> 1306
1067 <-> 668
1068 <-> 488, 1415, 1570
1069 <-> 482, 1116
1070 <-> 592, 1271
1071 <-> 468, 946
1072 <-> 225, 1072
1073 <-> 123, 1073
1074 <-> 898
1075 <-> 454, 876, 1129
1076 <-> 183, 1180
1077 <-> 339, 826
1078 <-> 256, 257, 940, 1501
1079 <-> 1031, 1092
1080 <-> 622
1081 <-> 1669, 1817
1082 <-> 1669, 1925
1083 <-> 689, 1682
1084 <-> 1413, 1764
1085 <-> 365, 448, 726, 1883
1086 <-> 1628
1087 <-> 298, 410, 939, 1951
1088 <-> 1483
1089 <-> 838
1090 <-> 152, 325, 964
1091 <-> 516, 638, 904, 1694
1092 <-> 1079, 1113
1093 <-> 461, 1355, 1686
1094 <-> 602, 678, 1094
1095 <-> 1726
1096 <-> 219
1097 <-> 1215
1098 <-> 255, 881, 1712
1099 <-> 442, 686, 853
1100 <-> 1100, 1684
1101 <-> 191, 1101
1102 <-> 512, 1449
1103 <-> 376, 1427
1104 <-> 1449
1105 <-> 684, 773, 991, 1118
1106 <-> 584, 826, 916
1107 <-> 1256
1108 <-> 914
1109 <-> 597, 1056
1110 <-> 285, 577
1111 <-> 431, 681, 1879, 1948
1112 <-> 1206, 1556
1113 <-> 131, 1092
1114 <-> 1160, 1821, 1889
1115 <-> 1491, 1571
1116 <-> 641, 1069
1117 <-> 551, 819, 908
1118 <-> 1105
1119 <-> 1054, 1950
1120 <-> 71
1121 <-> 1252
1122 <-> 416, 1196
1123 <-> 265, 382, 1509
1124 <-> 576, 1124, 1493, 1690
1125 <-> 261
1126 <-> 1155
1127 <-> 374, 1341
1128 <-> 150
1129 <-> 718, 1075
1130 <-> 1954
1131 <-> 191, 640
1132 <-> 1570
1133 <-> 140
1134 <-> 412
1135 <-> 1702, 1938
1136 <-> 955, 1513
1137 <-> 469, 1728
1138 <-> 1600, 1905
1139 <-> 671
1140 <-> 259, 1492, 1736
1141 <-> 436
1142 <-> 187, 557
1143 <-> 604
1144 <-> 1581
1145 <-> 991, 1672
1146 <-> 1571, 1683
1147 <-> 542
1148 <-> 1726
1149 <-> 506, 1001
1150 <-> 1371
1151 <-> 1151, 1403, 1967, 1993
1152 <-> 62, 1152
1153 <-> 500, 741
1154 <-> 220, 876, 1456
1155 <-> 48, 1126, 1375
1156 <-> 302, 1301
1157 <-> 279
1158 <-> 162, 1426, 1535
1159 <-> 933
1160 <-> 3, 128, 906, 1114
1161 <-> 1448, 1742
1162 <-> 660, 1435, 1579
1163 <-> 809, 1163, 1776
1164 <-> 994
1165 <-> 1803, 1870
1166 <-> 1921
1167 <-> 450
1168 <-> 759, 1168
1169 <-> 107
1170 <-> 1170
1171 <-> 1291, 1573
1172 <-> 663, 762
1173 <-> 196, 1764
1174 <-> 527
1175 <-> 170, 1786
1176 <-> 168, 1326
1177 <-> 529, 1598
1178 <-> 1037, 1989
1179 <-> 235, 1947
1180 <-> 1076, 1180
1181 <-> 1373, 1419
1182 <-> 690
1183 <-> 1183
1184 <-> 1224, 1504
1185 <-> 445
1186 <-> 327, 699
1187 <-> 203
1188 <-> 1598
1189 <-> 316, 931, 1561
1190 <-> 1603, 1647
1191 <-> 1704
1192 <-> 404
1193 <-> 359
1194 <-> 1594
1195 <-> 487, 1760
1196 <-> 876, 1122, 1475
1197 <-> 1942
1198 <-> 18, 67, 1943
1199 <-> 262, 1419
1200 <-> 1700
1201 <-> 991
1202 <-> 1457
1203 <-> 1543
1204 <-> 956, 1204
1205 <-> 600
1206 <-> 1112
1207 <-> 1553, 1910
1208 <-> 122
1209 <-> 131, 1622, 1771
1210 <-> 1446
1211 <-> 1386, 1595
1212 <-> 176, 976, 1868
1213 <-> 745, 1244, 1412
1214 <-> 13, 331, 614, 983
1215 <-> 292, 1097
1216 <-> 1723
1217 <-> 114, 1218
1218 <-> 1217
1219 <-> 98, 1450
1220 <-> 1959
1221 <-> 313, 880, 1008, 1614, 1688
1222 <-> 812, 1626
1223 <-> 907, 1853
1224 <-> 1184
1225 <-> 1241
1226 <-> 1799
1227 <-> 1227
1228 <-> 676, 1011
1229 <-> 968, 1994
1230 <-> 1650, 1914
1231 <-> 1787
1232 <-> 192
1233 <-> 492
1234 <-> 1614, 1628
1235 <-> 351, 929
1236 <-> 621
1237 <-> 1264
1238 <-> 218
1239 <-> 471, 655, 1369
1240 <-> 1015, 1256
1241 <-> 1225, 1241, 1336
1242 <-> 966, 1750
1243 <-> 1243, 1363
1244 <-> 542, 1213
1245 <-> 358
1246 <-> 1246
1247 <-> 377, 781, 938
1248 <-> 854, 950, 1347
1249 <-> 1856, 1930
1250 <-> 184
1251 <-> 377, 625, 1645
1252 <-> 786, 1121
1253 <-> 994, 1842
1254 <-> 124, 815, 1815
1255 <-> 1742
1256 <-> 1107, 1240
1257 <-> 449, 1964
1258 <-> 689, 849
1259 <-> 230
1260 <-> 289, 1898
1261 <-> 569
1262 <-> 1890
1263 <-> 65, 429
1264 <-> 258, 1237, 1416, 1770
1265 <-> 537
1266 <-> 1322, 1468, 1524, 1995
1267 <-> 1602, 1647
1268 <-> 222, 250, 623
1269 <-> 842
1270 <-> 260
1271 <-> 1070
1272 <-> 290, 859
1273 <-> 6, 343, 460
1274 <-> 119
1275 <-> 171, 1275, 1777
1276 <-> 650
1277 <-> 244, 1631
1278 <-> 1335
1279 <-> 433, 748
1280 <-> 1280
1281 <-> 856, 1452
1282 <-> 214
1283 <-> 1657, 1732
1284 <-> 770
1285 <-> 1285
1286 <-> 967
1287 <-> 803
1288 <-> 1353
1289 <-> 864, 1632
1290 <-> 92
1291 <-> 1171
1292 <-> 306, 1762
1293 <-> 1293
1294 <-> 1772
1295 <-> 421, 428
1296 <-> 234, 590, 792
1297 <-> 549, 618
1298 <-> 766, 1298, 1416, 1595, 1709
1299 <-> 275, 1884, 1981
1300 <-> 523, 703, 1428
1301 <-> 510, 896, 1156
1302 <-> 492, 999
1303 <-> 358
1304 <-> 467, 1304
1305 <-> 369, 1345
1306 <-> 261, 1066, 1946
1307 <-> 1568, 1845
1308 <-> 1994
1309 <-> 1334
1310 <-> 671
1311 <-> 320, 829, 1039, 1463, 1608
1312 <-> 949, 1590
1313 <-> 191
1314 <-> 1630
1315 <-> 757, 775, 1463
1316 <-> 1499
1317 <-> 636
1318 <-> 174
1319 <-> 449
1320 <-> 64, 500
1321 <-> 1532, 1678
1322 <-> 874, 1266, 1327
1323 <-> 283
1324 <-> 67, 1987
1325 <-> 1902
1326 <-> 1176, 1499
1327 <-> 1322
1328 <-> 1359
1329 <-> 88
1330 <-> 98, 1781
1331 <-> 918
1332 <-> 1430
1333 <-> 94, 1055, 1523
1334 <-> 1309, 1334, 1643
1335 <-> 1278, 1335
1336 <-> 1241
1337 <-> 1482, 1495
1338 <-> 1338
1339 <-> 469, 686
1340 <-> 717
1341 <-> 626, 1127, 1341, 1903
1342 <-> 348, 1944
1343 <-> 1343
1344 <-> 1407
1345 <-> 1305, 1760
1346 <-> 414, 1890
1347 <-> 288, 1248
1348 <-> 1575, 1576
1349 <-> 1583
1350 <-> 666, 941, 1462
1351 <-> 602, 1998
1352 <-> 45, 860, 1015
1353 <-> 1288, 1702
1354 <-> 1354
1355 <-> 1093, 1355, 1931
1356 <-> 616, 873, 1715
1357 <-> 1723
1358 <-> 1613
1359 <-> 1328, 1359
1360 <-> 1360
1361 <-> 554
1362 <-> 181, 914, 1627
1363 <-> 895, 1243, 1594
1364 <-> 1364
1365 <-> 1365, 1650
1366 <-> 129, 303, 1800
1367 <-> 35, 1522, 1716
1368 <-> 467
1369 <-> 1239
1370 <-> 824, 1502, 1576, 1943
1371 <-> 252, 1150
1372 <-> 495
1373 <-> 1181, 1698
1374 <-> 1006, 1999
1375 <-> 279, 1155, 1921
1376 <-> 509, 1651
1377 <-> 1796
1378 <-> 186, 569, 1552
1379 <-> 80, 255, 1634
1380 <-> 236, 734
1381 <-> 248, 415, 435, 976, 1022
1382 <-> 368
1383 <-> 836, 1913
1384 <-> 1425
1385 <-> 324, 682, 1640
1386 <-> 1211
1387 <-> 1494
1388 <-> 731, 734, 1466
1389 <-> 127
1390 <-> 12
1391 <-> 586, 1657
1392 <-> 848
1393 <-> 896, 987
1394 <-> 647, 753
1395 <-> 1429
1396 <-> 563, 797, 1434
1397 <-> 772
1398 <-> 1913
1399 <-> 702, 1572
1400 <-> 1876
1401 <-> 630, 906
1402 <-> 633
1403 <-> 1151
1404 <-> 1661
1405 <-> 206, 852
1406 <-> 352, 1743
1407 <-> 689, 1344
1408 <-> 784, 850
1409 <-> 816, 838, 930
1410 <-> 270, 524
1411 <-> 1468, 1793
1412 <-> 445, 1213
1413 <-> 754, 795, 1084
1414 <-> 140, 254
1415 <-> 1068
1416 <-> 1264, 1298
1417 <-> 780
1418 <-> 950
1419 <-> 1181, 1199
1420 <-> 160
1421 <-> 340
1422 <-> 1422, 1860
1423 <-> 34, 1971
1424 <-> 1991
1425 <-> 1384, 1450
1426 <-> 1158
1427 <-> 84, 179, 1103, 1706
1428 <-> 1300
1429 <-> 528, 1395
1430 <-> 1332, 1430
1431 <-> 551
1432 <-> 365
1433 <-> 900
1434 <-> 138, 877, 894, 1059, 1396
1435 <-> 1162
1436 <-> 887, 1859, 1949
1437 <-> 114, 386, 843, 1855
1438 <-> 101, 500, 1581
1439 <-> 1449
1440 <-> 81, 1766
1441 <-> 1767
1442 <-> 385, 409, 653
1443 <-> 233, 1443
1444 <-> 1059, 1670, 1742
1445 <-> 1669
1446 <-> 444, 1210
1447 <-> 1842
1448 <-> 1161
1449 <-> 1102, 1104, 1439
1450 <-> 155, 1219, 1425, 1677, 1727
1451 <-> 969, 1584
1452 <-> 399, 1281
1453 <-> 265
1454 <-> 507, 1052, 1834
1455 <-> 346, 358
1456 <-> 1154, 1786
1457 <-> 1202, 1619
1458 <-> 383
1459 <-> 1718, 1729
1460 <-> 604
1461 <-> 131, 538, 1740
1462 <-> 1350, 1530
1463 <-> 1311, 1315, 1582, 1982
1464 <-> 1817, 1906
1465 <-> 1465
1466 <-> 420, 1388
1467 <-> 530, 868
1468 <-> 855, 1266, 1411
1469 <-> 651, 763
1470 <-> 14, 1829
1471 <-> 644, 1775
1472 <-> 202, 829, 1562
1473 <-> 13, 211, 1891
1474 <-> 1813, 1852
1475 <-> 1196
1476 <-> 986, 1537
1477 <-> 1481
1478 <-> 133, 151, 1932
1479 <-> 1538
1480 <-> 79, 1565
1481 <-> 1477, 1481
1482 <-> 536, 1337
1483 <-> 465, 1088
1484 <-> 1553, 1974
1485 <-> 88, 727
1486 <-> 1582
1487 <-> 972, 1731, 1896
1488 <-> 944, 1845
1489 <-> 198
1490 <-> 1926
1491 <-> 565, 949, 1115, 1983
1492 <-> 1140
1493 <-> 1124
1494 <-> 1387, 1814
1495 <-> 175, 1048, 1337
1496 <-> 23
1497 <-> 166
1498 <-> 312, 810, 858, 1727
1499 <-> 1316, 1326, 1586
1500 <-> 645
1501 <-> 1078, 1847
1502 <-> 1370
1503 <-> 1992
1504 <-> 832, 1184
1505 <-> 78
1506 <-> 52, 860
1507 <-> 1642, 1647
1508 <-> 1048, 1508
1509 <-> 246, 1123
1510 <-> 547, 1876
1511 <-> 385, 389
1512 <-> 37, 903
1513 <-> 517, 1136
1514 <-> 342, 567, 1641
1515 <-> 815
1516 <-> 1722, 1992
1517 <-> 349, 646, 1517
1518 <-> 29, 801
1519 <-> 1676
1520 <-> 771
1521 <-> 752, 1754, 1916
1522 <-> 521, 1367
1523 <-> 627, 1333
1524 <-> 870, 1266
1525 <-> 430, 722
1526 <-> 583, 1878
1527 <-> 985
1528 <-> 741
1529 <-> 1559, 1954
1530 <-> 51, 598, 1462
1531 <-> 467, 1842
1532 <-> 337, 386, 755, 1321
1533 <-> 887
1534 <-> 1740
1535 <-> 827, 915, 1158
1536 <-> 334, 1536
1537 <-> 522, 1476
1538 <-> 812, 1479
1539 <-> 277, 802, 898
1540 <-> 660, 1937
1541 <-> 261, 1065
1542 <-> 743
1543 <-> 983, 1203
1544 <-> 403, 1005
1545 <-> 920
1546 <-> 839
1547 <-> 30
1548 <-> 58
1549 <-> 292, 1063
1550 <-> 925, 1033
1551 <-> 1618
1552 <-> 315, 1378, 1552
1553 <-> 1207, 1484
1554 <-> 192
1555 <-> 1555, 1710
1556 <-> 591, 1112
1557 <-> 760
1558 <-> 653, 1934
1559 <-> 260, 1529
1560 <-> 634, 1061, 1812
1561 <-> 706, 1189
1562 <-> 267, 922, 1472
1563 <-> 566, 723
1564 <-> 215, 1022
1565 <-> 1480
1566 <-> 476, 707, 930
1567 <-> 1042
1568 <-> 1307
1569 <-> 861
1570 <-> 343, 671, 1068, 1132
1571 <-> 527, 1115, 1146, 1630
1572 <-> 1399, 1591
1573 <-> 1171, 1723
1574 <-> 267
1575 <-> 76, 1348
1576 <-> 881, 1348, 1370, 1646
1577 <-> 1687
1578 <-> 503
1579 <-> 452, 1162, 1660
1580 <-> 1926
1581 <-> 184, 1144, 1438, 1648
1582 <-> 1463, 1486
1583 <-> 1349, 1583, 1644
1584 <-> 1451
1585 <-> 691, 1706
1586 <-> 1499, 1676
1587 <-> 161, 208, 1786
1588 <-> 661, 749
1589 <-> 587
1590 <-> 321, 1312
1591 <-> 1572, 1844
1592 <-> 419
1593 <-> 1593
1594 <-> 962, 1194, 1363
1595 <-> 194, 1211, 1298, 1754
1596 <-> 1031
1597 <-> 688, 999, 1816
1598 <-> 94, 1177, 1188
1599 <-> 397
1600 <-> 912, 1138
1601 <-> 743
1602 <-> 405, 1267
1603 <-> 1190
1604 <-> 643, 936
1605 <-> 514, 521, 680
1606 <-> 1606
1607 <-> 746
1608 <-> 1311, 1608
1609 <-> 483, 790
1610 <-> 305, 1610
1611 <-> 268, 477
1612 <-> 371
1613 <-> 336, 1358, 1642
1614 <-> 1221, 1234, 1732
1615 <-> 287
1616 <-> 19, 143
1617 <-> 327
1618 <-> 38, 283, 436, 1551
1619 <-> 1457, 1817
1620 <-> 210, 1791
1621 <-> 1976
1622 <-> 1209, 1928
1623 <-> 139
1624 <-> 887
1625 <-> 577
1626 <-> 665, 1222
1627 <-> 1362
1628 <-> 1086, 1234
1629 <-> 848
1630 <-> 1314, 1571
1631 <-> 1277
1632 <-> 304, 795, 1289
1633 <-> 454, 808
1634 <-> 404, 1379
1635 <-> 900
1636 <-> 1006
1637 <-> 1637
1638 <-> 439, 595, 879
1639 <-> 1639
1640 <-> 1385
1641 <-> 1514
1642 <-> 1507, 1613
1643 <-> 556, 615, 744, 1334
1644 <-> 1583
1645 <-> 1251
1646 <-> 223, 1576
1647 <-> 1190, 1267, 1507
1648 <-> 441, 1581
1649 <-> 56, 470
1650 <-> 190, 663, 1230, 1365, 1875
1651 <-> 1376
1652 <-> 826, 1027
1653 <-> 46, 163, 165
1654 <-> 280, 380, 1808
1655 <-> 533, 1668
1656 <-> 64, 899, 1746
1657 <-> 1283, 1391, 1941
1658 <-> 564
1659 <-> 1824
1660 <-> 278, 1579
1661 <-> 2, 1404
1662 <-> 1757
1663 <-> 1663
1664 <-> 1664
1665 <-> 38, 1015
1666 <-> 907
1667 <-> 572, 1824, 1830
1668 <-> 1655
1669 <-> 921, 1081, 1082, 1445
1670 <-> 407, 1444
1671 <-> 1671
1672 <-> 505, 1145
1673 <-> 1673
1674 <-> 438, 611
1675 <-> 198
1676 <-> 1519, 1586
1677 <-> 1450
1678 <-> 1321, 1819
1679 <-> 679
1680 <-> 790
1681 <-> 1938
1682 <-> 1083, 1997
1683 <-> 1146
1684 <-> 981, 1100
1685 <-> 947
1686 <-> 1093
1687 <-> 1000, 1577, 1713
1688 <-> 1221
1689 <-> 1708
1690 <-> 642, 1124
1691 <-> 495
1692 <-> 1821
1693 <-> 359, 560
1694 <-> 266, 601, 1091, 1933
1695 <-> 859
1696 <-> 327, 695
1697 <-> 804
1698 <-> 1373, 1767
1699 <-> 1985
1700 <-> 796, 1200
1701 <-> 116, 347, 706
1702 <-> 1135, 1353
1703 <-> 715, 906
1704 <-> 179, 1191
1705 <-> 80
1706 <-> 1427, 1585
1707 <-> 1927
1708 <-> 66, 142, 1689
1709 <-> 553, 891, 1298
1710 <-> 1555
1711 <-> 919
1712 <-> 1098, 1915
1713 <-> 242, 804, 1687
1714 <-> 666
1715 <-> 1356
1716 <-> 1367, 1716
1717 <-> 444
1718 <-> 1020, 1459
1719 <-> 1852
1720 <-> 77
1721 <-> 758
1722 <-> 470, 909, 1516, 1783
1723 <-> 454, 585, 928, 1216, 1357, 1573
1724 <-> 255
1725 <-> 587, 1725
1726 <-> 392, 872, 1095, 1148
1727 <-> 1450, 1498
1728 <-> 1137
1729 <-> 1459
1730 <-> 416
1731 <-> 710, 1487, 1904
1732 <-> 782, 1283, 1614
1733 <-> 1880
1734 <-> 491
1735 <-> 214, 286
1736 <-> 32, 698, 1140
1737 <-> 864
1738 <-> 388, 475, 582
1739 <-> 551
1740 <-> 90, 1461, 1534
1741 <-> 411
1742 <-> 996, 1161, 1255, 1444
1743 <-> 221, 1406, 1743, 1748
1744 <-> 501, 1053
1745 <-> 70
1746 <-> 109, 1656
1747 <-> 769, 1005, 1844
1748 <-> 1743
1749 <-> 1, 525
1750 <-> 647, 1242
1751 <-> 1907
1752 <-> 842
1753 <-> 240, 1793
1754 <-> 1521, 1595
1755 <-> 1755
1756 <-> 403
1757 <-> 1662, 1757
1758 <-> 716
1759 <-> 1064
1760 <-> 1195, 1345
1761 <-> 35
1762 <-> 1292, 1762
1763 <-> 173, 1869
1764 <-> 156, 996, 1084, 1173
1765 <-> 656, 1851
1766 <-> 832, 1440
1767 <-> 229, 276, 1441, 1698
1768 <-> 83, 301, 571
1769 <-> 1998
1770 <-> 153, 1264
1771 <-> 1209
1772 <-> 555, 1294, 1897
1773 <-> 1773, 1858
1774 <-> 1961
1775 <-> 30, 1471
1776 <-> 1163
1777 <-> 1275
1778 <-> 639, 1013, 1043, 1905
1779 <-> 672, 712
1780 <-> 1780
1781 <-> 247, 1330
1782 <-> 160, 288, 371
1783 <-> 10, 211, 1722
1784 <-> 216, 913
1785 <-> 244
1786 <-> 593, 1175, 1456, 1587
1787 <-> 200, 518, 1231
1788 <-> 887
1789 <-> 294, 796, 978
1790 <-> 693, 1902
1791 <-> 361, 1620
1792 <-> 559, 1828
1793 <-> 839, 1051, 1411, 1753
1794 <-> 1837, 1969
1795 <-> 361
1796 <-> 163, 508, 1377
1797 <-> 266
1798 <-> 853
1799 <-> 250, 1226
1800 <-> 120, 498, 1366
1801 <-> 755
1802 <-> 180, 363
1803 <-> 637, 1165
1804 <-> 796
1805 <-> 539, 1005
1806 <-> 1806
1807 <-> 648
1808 <-> 904, 914, 1654
1809 <-> 79, 1054
1810 <-> 1838
1811 <-> 472
1812 <-> 1560, 1812
1813 <-> 942, 1474, 1891
1814 <-> 700, 1494
1815 <-> 1254
1816 <-> 329, 1597
1817 <-> 1081, 1464, 1619, 1891
1818 <-> 252, 779
1819 <-> 1678
1820 <-> 529
1821 <-> 525, 1114, 1692
1822 <-> 73
1823 <-> 652
1824 <-> 749, 823, 1659, 1667
1825 <-> 984
1826 <-> 236
1827 <-> 357, 773
1828 <-> 270, 1792, 1869
1829 <-> 1470, 1831
1830 <-> 867, 1667
1831 <-> 975, 1829
1832 <-> 1832
1833 <-> 755
1834 <-> 825, 1454
1835 <-> 185, 740
1836 <-> 830, 1881
1837 <-> 1794
1838 <-> 485, 509, 998, 1810, 1943
1839 <-> 566, 846
1840 <-> 1840
1841 <-> 1951
1842 <-> 1253, 1447, 1531
1843 <-> 685, 1022
1844 <-> 375, 948, 1591, 1747
1845 <-> 88, 788, 1307, 1488
1846 <-> 202
1847 <-> 287, 1501
1848 <-> 300, 1969
1849 <-> 1849
1850 <-> 232, 426
1851 <-> 1765
1852 <-> 1474, 1719
1853 <-> 500, 1223
1854 <-> 616
1855 <-> 1437
1856 <-> 75, 94, 1249
1857 <-> 793
1858 <-> 1773
1859 <-> 318, 1436
1860 <-> 1422
1861 <-> 736, 1947
1862 <-> 798, 871, 1890
1863 <-> 428
1864 <-> 165
1865 <-> 258
1866 <-> 271, 968
1867 <-> 0
1868 <-> 382, 1212
1869 <-> 327, 1763, 1828
1870 <-> 296, 423, 1165, 1931
1871 <-> 457
1872 <-> 53, 1973
1873 <-> 269
1874 <-> 1874
1875 <-> 52, 1650
1876 <-> 578, 1400, 1510
1877 <-> 543
1878 <-> 1526
1879 <-> 610, 1111, 1879
1880 <-> 197, 1733
1881 <-> 1053, 1836
1882 <-> 530
1883 <-> 1085
1884 <-> 1299
1885 <-> 30
1886 <-> 453, 1907, 1909
1887 <-> 598
1888 <-> 811
1889 <-> 1114
1890 <-> 1262, 1346, 1862
1891 <-> 291, 1003, 1473, 1813, 1817
1892 <-> 1892
1893 <-> 557
1894 <-> 1997
1895 <-> 62, 862
1896 <-> 1487
1897 <-> 1772
1898 <-> 1260, 1902
1899 <-> 493
1900 <-> 337, 393, 961
1901 <-> 147, 1940
1902 <-> 379, 1325, 1790, 1898
1903 <-> 1341
1904 <-> 599, 1064, 1731
1905 <-> 714, 1138, 1778
1906 <-> 1464
1907 <-> 1751, 1886
1908 <-> 696
1909 <-> 769, 1886
1910 <-> 1207
1911 <-> 628
1912 <-> 319, 790, 1912
1913 <-> 945, 1383, 1398
1914 <-> 778, 893, 1230
1915 <-> 1712, 1917
1916 <-> 164, 230, 1521
1917 <-> 387, 1915
1918 <-> 59, 350, 451, 612, 855, 1032
1919 <-> 776, 1048
1920 <-> 1003
1921 <-> 1166, 1375
1922 <-> 5, 691
1923 <-> 99
1924 <-> 520
1925 <-> 1082
1926 <-> 803, 1490, 1580
1927 <-> 162, 1707
1928 <-> 438, 1622
1929 <-> 67, 795
1930 <-> 1249
1931 <-> 1355, 1870
1932 <-> 68, 1478
1933 <-> 995, 1694
1934 <-> 1558
1935 <-> 370, 694
1936 <-> 545
1937 <-> 360, 1540
1938 <-> 756, 1135, 1681
1939 <-> 694
1940 <-> 531, 728, 1901
1941 <-> 1657
1942 <-> 493, 881, 1197
1943 <-> 1198, 1370, 1838
1944 <-> 1342
1945 <-> 438
1946 <-> 299, 1306
1947 <-> 933, 1179, 1861
1948 <-> 1111
1949 <-> 318, 502, 1436
1950 <-> 861, 1119
1951 <-> 159, 1087, 1841
1952 <-> 446
1953 <-> 714
1954 <-> 1130, 1529
1955 <-> 725, 764
1956 <-> 550
1957 <-> 546
1958 <-> 987
1959 <-> 1036, 1220
1960 <-> 49, 356, 1049
1961 <-> 1774, 1961
1962 <-> 393
1963 <-> 1963
1964 <-> 1031, 1257
1965 <-> 903, 1001
1966 <-> 399
1967 <-> 1151
1968 <-> 732
1969 <-> 972, 1794, 1848
1970 <-> 338, 592
1971 <-> 1423
1972 <-> 590
1973 <-> 596, 1872
1974 <-> 1000, 1484
1975 <-> 1975
1976 <-> 1621, 1976
1977 <-> 573, 783
1978 <-> 87
1979 <-> 666, 771
1980 <-> 900
1981 <-> 262, 517, 1021, 1299
1982 <-> 1042, 1463
1983 <-> 668, 1491
1984 <-> 123
1985 <-> 791, 1699
1986 <-> 246, 934, 1986
1987 <-> 1324
1988 <-> 850, 866
1989 <-> 139, 1178
1990 <-> 161, 700
1991 <-> 635, 1424
1992 <-> 1503, 1516
1993 <-> 1151
1994 <-> 958, 1229, 1308
1995 <-> 1266
1996 <-> 844
1997 <-> 1682, 1894
1998 <-> 1351, 1769
1999 <-> 1374"""
  
  
  class Prog( val name : String, val links : ListBuffer[Prog] = ListBuffer[Prog]() ){
    override def toString() : String = {
      
      var res = "("
      
      res += name + " ["
      
      for( p <- links ){
        res += p.name
      }
      
      res += "]"
      
      res += ")"
      
      res
    }
  }
  
  def main( args : Array[String] ) : Unit = {
    Console.println("day 12")
    
    val lines = Common.toLines(data)
    
    // convert 
    val progs = convert( lines )
    associate( lines, progs )
    Console.println(progs)
    
    // find all associate to 0
    // val group = makeGroup( "0", progs )
    // Console.println(group)
    // Console.println(group.size)
    val gs = makeGroups(progs)
    Console.println( gs )
    Console.println( gs.size )

  }
  
  def makeGroups( progs : Map[String,Prog] ) : List[List[Prog]] = {
    
    val groups = ListBuffer[List[Prog]]()
    val remaining = HashMap[String,Prog]()
    remaining ++= progs
    
    while( !remaining.isEmpty ){
      
      // take the first key and make a group
      val nm = remaining.keySet.head
      val group = makeGroup( nm, remaining.toMap )
      
      // remove this group from remaining
      for( p <- group ){
        remaining.remove(p.name)
      }
      
      groups += group
      
    }
    
    
    groups.toList

    
  }
  
  def makeGroup( name : String, progs : Map[String,Prog] ) : List[Prog] = {
    
    val ps = HashMap[String,Prog]() 
    
    
    
    def inner( prog : Prog ) : Unit = {
      
      if( !ps.contains(prog.name) ){
        
        ps += (prog.name -> prog)
        
        prog.links.foreach( inner( _ ) )
        
      }
      
    }
    
    val start = progs(name)
    inner(start)
    
    
    ps.values.toList
    
  }
  
  def associate( lines : List[String], progs : Map[String,Prog] ) : Unit = {
    
    for( line <- lines ){
      
      val parts = line.split(' ')
      
      // find the prog from the map
      val p = progs(parts(0))
      
      for( i <- 2 until parts.size ){
        val id = parts(i).replace(",", "" ).trim()
        val cp = progs(id)
        if( !p.name.equals(id) ){
          p.links += cp
        }
      }
    }
    
  }
  
  def convert( lines : List[String] ) : Map[String,Prog] = {
    
    val progs = HashMap[String,Prog]()
    
    for( line <- lines ) {
      
      val p = convert(line)
      progs += (p.name -> p)
    }
    
    progs.toMap
    
  }
  
  def convert( line : String ) : Prog = {
    val parts = line.split(' ')
    new Prog( parts(0) ) 
  }
  
}