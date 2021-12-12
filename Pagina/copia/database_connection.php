<?php 

$connect = new PDO("mysql:host=localhost;dbname=projecte", "programa", "jy1616jy");

// mostrar en la pagina de producte la seva informacio 
function get_single_by_id($id) {
    global $connect;
    $singles = $connect->query("select * from categoria b, material c, fet d, producte a where d.codi_mat=c.codi and d.id_prod = a.id and a.codi_cat = b.codi and id='$id'");
    foreach ($singles as $single){
        return $single;

    }
}


// En la pagina de cada producte mostrar els productes semblants amb tota la seva informacio
function get_single_by_id2($id2) {
    global $connect;
    $singles2 = $connect->query("select * from producte a,producte b, equival c, categoria d, material f, fet e where a.id = c.id_producte1  and b.id = c.id_producte2  and d.codi = a.codi_cat and e.id_prod = a.id and f.codi = e.codi_mat and a.id='$id2';
    ");
    
        return $singles2;
}
?>


