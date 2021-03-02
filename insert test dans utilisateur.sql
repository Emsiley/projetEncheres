USE [BDD_ENCHERES]
GO

INSERT INTO [dbo].[UTILISATEURS]
           ([pseudo]
           ,[nom]
           ,[prenom]
           ,[email]
           ,[telephone]
           ,[rue]
           ,[code_postal]
           ,[ville]
           ,[mot_de_passe]
           ,[credit]
           ,[administrateur])
     VALUES
           ('test'
           ,'test'
           ,'test'
           ,'test@test.fr'
           ,'0600000000'
           ,'rue test'
           ,'07400'
           ,'ville test'
           ,'mdptest'
           ,1000
           ,0)
GO


