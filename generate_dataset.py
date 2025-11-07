# generate_dataset.py
import csv
import random
from datetime import datetime, timedelta

def generate_categories():
    """Génère 2000 catégories"""
    with open('categories.csv', 'w', newline='', encoding='utf-8') as f:
        writer = csv.writer(f)
        writer.writerow(['code', 'name', 'updated_at'])
        
        for i in range(1, 2001):
            code = f"CAT{i:04d}"
            name = f"Catégorie Produits {i}"
            updated_at = generate_random_date()
            writer.writerow([code, name, updated_at])
    
    print("✅ categories.csv généré avec 2000 catégories")

def generate_items():
    """Génère ~100 000 items (~50 par catégorie)"""
    with open('items.csv', 'w', newline='', encoding='utf-8') as f:
        writer = csv.writer(f)
        writer.writerow(['sku', 'name', 'price', 'stock', 'category_id', 'updated_at'])
        
        item_count = 0
        for category_id in range(1, 2001):
            # Variation aléatoire autour de 50 items/catégorie
            items_per_category = random.randint(48, 52)
            
            for j in range(items_per_category):
                sku = f"SKU{category_id:04d}{j:03d}"
                name = f"Produit {category_id}-{j} {generate_product_name()}"
                price = round(random.uniform(1.0, 1000.0), 2)
                stock = random.randint(0, 1000)
                updated_at = generate_random_date()
                
                writer.writerow([sku, name, price, stock, category_id, updated_at])
                item_count += 1
    
    print(f"✅ items.csv généré avec {item_count} items")

def generate_product_name():
    """Génère un nom de produit aléatoire"""
    prefixes = ["Premium", "Basic", "Deluxe", "Standard", "Professional", 
                "Economy", "Luxury", "Essential", "Advanced", "Classic",
                "Modern", "Vintage", "Digital", "Analog", "Wireless",
                "Portable", "Stationary", "Compact", "Large", "Small"]
    
    types = ["Téléphone", "Ordinateur", "Tablette", "Casque", "Clavier", 
             "Souris", "Écran", "Imprimante", "Camera", "Enceinte",
             "Laptop", "Smartphone", "Monitor", "Keyboard", "Mouse",
             "Headset", "Speaker", "Tablet", "Printer", "Scanner"]
    
    suffixes = ["Pro", "Plus", "Max", "Mini", "Lite", "Ultra", "Edge", "X"]
    
    name_parts = [
        f"{random.choice(prefixes)} {random.choice(types)}",
        f"{random.choice(types)} {random.choice(suffixes)}",
        f"{random.choice(prefixes)} {random.choice(types)} {random.choice(suffixes)}"
    ]
    
    return random.choice(name_parts)

def generate_random_date():
    """Génère une date aléatoire dans les 2 dernières années"""
    end_date = datetime.now()
    start_date = end_date - timedelta(days=730)  # 2 ans
    random_date = start_date + timedelta(
        seconds=random.randint(0, int((end_date - start_date).total_seconds()))
    )
    return random_date.strftime('%Y-%m-%d %H:%M:%S')

def verify_data():
    """Vérifie la qualité des données générées"""
    # Compter les catégories
    with open('categories.csv', 'r', encoding='utf-8') as f:
        reader = csv.reader(f)
        next(reader)  # skip header
        category_count = sum(1 for row in reader)
    
    # Compter les items et vérifier la distribution
    category_items = {}
    with open('items.csv', 'r', encoding='utf-8') as f:
        reader = csv.reader(f)
        next(reader)  # skip header
        item_count = 0
        for row in reader:
            item_count += 1
            category_id = int(row[4])
            category_items[category_id] = category_items.get(category_id, 0) + 1
    
    print(f"📊 VÉRIFICATION DES DONNÉES:")
    print(f"   Categories: {category_count}")
    print(f"   Items: {item_count}")
    print(f"   Items par catégorie: min={min(category_items.values())}, max={max(category_items.values())}, avg={sum(category_items.values())/len(category_items):.1f}")

if __name__ == "__main__":
    print("🚀 Génération du jeu de données...")
    generate_categories()
    generate_items()
    verify_data()
    print("🎉 Génération terminée avec succès!")