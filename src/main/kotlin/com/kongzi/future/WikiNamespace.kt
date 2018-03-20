package com.kongzi.future

enum class WikiNamespace (val ns: Int) {
    Article(0),             Talk(1),
    User(2),                User_Talk(3),
    Wikipedia(4),           Wikipedia_Talk(5),
    File(6),                File_Talk(7),
    MediaWiki(8),           MediaWiki_Talk(9),
    Template(10),           Template_Talk(11),
    Help(12),               Help_Talk(13),
    Category(14),           Category_Talk(15),
    Portal(100),            Portal_Talk(101),
    Book(108),              Book_Talk(109),
    Draft(118),             Draft_Talk(119),
    Education_Program(446), Education_Program_Talk(447),
    TimedText(710),         TimedText_Talk(711),
    Module(828),            Module_Talk(829),
    Gadget(2300),           Gadget_Talk(2301),
    Gadget_Definition(2302),Gadget_Definition_Talk(2303)
}