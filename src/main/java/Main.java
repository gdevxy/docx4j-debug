import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;

import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.AltChunkType;

public class Main {

	public static void main(String[] args) throws Exception {

		var html = """
			<html>
				<head>
					<style>
						.Heading1 {
							font-weight: normal;
							line-height: 1.1;
							margin-top: 0.2em;
							margin-bottom: 0.2em;
							background-color: transparent;
							color: #404040;
							font-family: Calibri;
							font-size: 24pt;
						}
					</style>
				</head>
				<body>
					<h1 class="Heading1">H1</h1>
					<h2 style="font-weight: normal; line-height: 1.1; margin-top: 0.2em; margin-bottom: 0.2em; background-color: transparent; color: #404040; font-family: Calibri; font-size: 26pt;">H2</h2>
				</body>
			</html>""";

		var mlp = WordprocessingMLPackage.createPackage();
		var mdp = mlp.getMainDocumentPart();

		mdp.addAltChunk(AltChunkType.Xhtml, new ByteArrayInputStream(html.getBytes(StandardCharsets.UTF_8)));
		mdp.convertAltChunks();

		mlp.save(new FileOutputStream("myFile.docx"));
	}

}
