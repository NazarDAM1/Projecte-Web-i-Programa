<?php



include('database_connection.php');

// depen del preu i checkbox seleccionat fara la consulta a la base de dades per exemple si hem seleccionat un checkbox amb el nom ordenadors que es un nom de categoria al final de la consulta $query afegira  AND nom_mat in "ordenadors" 



if(isset($_POST["action"]))
{
	$query = "
	select * from categoria b, material c, fet d, producte a where d.codi_mat=c.codi and d.id_prod = a.id and a.codi_cat = b.codi 
	";
	if(isset($_POST["minimum_price"], $_POST["maximum_price"]) && !empty($_POST["minimum_price"]) && !empty($_POST["maximum_price"]))
	{
		$query .= "
		 AND preu BETWEEN '".$_POST["minimum_price"]."' AND '".$_POST["maximum_price"]."'
		";
	}
	if(isset($_POST["nom_cat"]))
	{
		$cat_filter = implode("','", $_POST["nom_cat"]);
		$query .= "
		 AND nom_cat IN('".$cat_filter."')
		";
	}
	if(isset($_POST["nom_mat"]))
	{
		$mat_filter = implode("','", $_POST["nom_mat"]);
		$query .= "
		 AND nom_mat IN('".$mat_filter."')
		";
	}
	if(isset($_POST["nom_prod"]))
	{
		$nom_prod_filter = implode("','", $_POST["nom_prod"]);
		$query .= "
		ORDER BY nom_prod ASC;
		";
	}
	if(isset($_POST["preu"]))
	{
		$mat_filter = implode("','", $_POST["preu"]);
		$query .= "
		ORDER BY preu DESC;
		";
	}


    // amb la informacio de la consulta que esta en la variable $query crea productes a la pagina principal

	$statement = $connect->prepare($query);
	$statement->execute();
	$result = $statement->fetchAll();
	$total_row = $statement->rowCount();
	$output = '';
	if($total_row > 0) // si hi han mes rows que 0 creara el producte
	{
		foreach($result as $row)
		{
			$output .= '
			<a href="producte.php?id='.$row['id'].'">
			<div class="producte">
			
				<img src='. $row['img'].' alt="'. $row['nom_prod'].'"></img>
				<div class="descripcio">
					<a class="nom_prod_a">Nom:&nbsp</a>
					<a class="nom_prod">'. $row['nom_prod'].'</a><br>
					<a>Descripcio: </a>
					<a>'. $row['descripcio'].'</a>
				</div>
				<div class="categoria">
					<a>Categoria:&nbsp </a>
					<a>'. $row['nom_cat'].'</a>
	
				</div>
				<div class="material">
					<a>Material: &nbsp </a>
					<a>'. $row['nom_mat'].'</a>
				</div>
				<div class="quantitat">
					<a>Quantitat: &nbsp</a>
					<a>'. $row['quantitat'].'</a>
				</div>
				<div class="preu2">
					<a>Preu:&nbsp </a>
					<a> '. $row['preu'].' $</a>
				</div>
			</div>
			</a>
			';

			
		}
	}
	else  // si fent la consulta no troba cap producte  mostrar aquesta imatge
	{
		$output = '<img class="producteNoTrobat" src="img/img_logo/res.png"/ alt="no ha trobat res">';
	}
	echo $output;
}
