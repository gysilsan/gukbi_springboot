SELECT tb_upload.*, tb_attach.fname, tb_attach.fpath 
		FROM tb_upload INNER JOIN tb_attach 
		ON tb_upload.num = tb_attach.num;