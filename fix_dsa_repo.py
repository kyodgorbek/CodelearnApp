
def fix_kotlin_file(file_path):
    with open(file_path, 'r', encoding='utf-8') as f:
        lines = f.readlines()

    new_lines = []
    skip_next = False
    
    for i in range(len(lines)):
        line = lines[i]
        stripped = line.strip()
        
        # Check for duplicate codeExample = """
        if 'codeExample = """' in line:
            # Check if next line is identical (ignoring whitespace differences or exact match?)
            # In the file view, the indent looked slightly different for some, but let's check exact content match logic
            # actually better to just check if the PREVIOUS line was the same
            if len(new_lines) > 0 and 'codeExample = """' in new_lines[-1]:
                 # It's a duplicate, skip adding this one
                 continue
        
        # Check for duplicate """.trimIndent()
        if '""".trimIndent()' in line:
            # Check if previous line was the same
            if len(new_lines) > 0 and '""".trimIndent()' in new_lines[-1]:
                continue
                
        new_lines.append(line)

    with open(file_path, 'w', encoding='utf-8') as f:
        f.writelines(new_lines)

file_path = r'c:\Users\Edgar\AndroidStudioProjects\CodelearnApp\app\src\main\java\com\example\codelearnapp\data\repository\KotlinDsaRepositoryImpl.kt'
fix_kotlin_file(file_path)
print("File fixed.")
