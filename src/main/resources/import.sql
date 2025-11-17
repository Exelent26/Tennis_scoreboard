INSERT INTO Players(Name)
SELECT 'Rafael Nadal'
    WHERE NOT EXISTS (SELECT 1 FROM Players WHERE Name = 'Rafael Nadal');