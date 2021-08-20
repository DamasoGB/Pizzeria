SELECT pizza.name AS "Sin ingredientes"
FROM pizza
LEFT JOIN pizza_ingredient
ON pizza.id = pizza_ingredient.id_pizza
WHERE pizza_ingredient.id_ingredient IS NULL;