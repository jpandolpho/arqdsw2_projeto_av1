package br.edu.ifsp.arqdsw2.projeto_av1.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/image")
public class ImagemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String PROFILE_DIR = "C:\\calmind";
	private static final String OFFICE_DIR = "C:\\calmind\\locations";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nomeArquivo = request.getParameter("name");
		if (nomeArquivo == null || nomeArquivo.isEmpty()) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Nome da imagem não	fornecido.");
			return;
		}
		File imagem;
		if("office".equals(request.getParameter("type"))) {
			imagem = new File(OFFICE_DIR, nomeArquivo);
		}else if("profile".equals(request.getParameter("type"))) {
			imagem = new File(PROFILE_DIR, nomeArquivo);
		}else {
			return;
		}
		if (!imagem.exists() || imagem.isDirectory()) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND, "Imagem não encontrada.");
			return;
		}
		String contentType = getServletContext().getMimeType(imagem.getName());

		if (contentType == null) {
			contentType = "application/octet-stream";
		}
		response.setContentType(contentType);
		response.setContentLengthLong(imagem.length());
		// Transfere o arquivo para a saída
		try (FileInputStream in = new FileInputStream(imagem); ServletOutputStream out = response.getOutputStream()) {
			byte[] buffer = new byte[8192];
			int bytesRead;
			while ((bytesRead = in.read(buffer)) > 0) {
				out.write(buffer, 0, bytesRead);
			}
		}
	}
}
