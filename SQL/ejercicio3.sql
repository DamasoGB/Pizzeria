SELECT P.name AS Nombre, CONCAT(SUM(price), ' €') AS Total
FROM pizza P, ingredient I, pizza_ingredient PI 
WHERE (P.id = PI.id_pizza AND I.id = PI.id_ingredient)
AND P.name = 'carbonara';