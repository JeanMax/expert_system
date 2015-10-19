#******************************************************************************#
#                                                                              #
#                                                         :::      ::::::::    #
#    Makefile                                           :+:      :+:    :+:    #
#                                                     +:+ +:+         +:+      #
#    By: mcanal <mcanal@student.42.fr>              +#+  +:+       +#+         #
#                                                 +#+#+#+#+#+   +#+            #
#    Created: 2014/11/29 13:16:03 by mcanal            #+#    #+#              #
#    Updated: 2015/10/19 14:31:06 by mcanal           ###   ########.fr        #
#                                                                              #
#******************************************************************************#

NAME = expert_system

SRC = Main.scala Parse.scala

DIR = src
VPATH =	src
O_DIR = obj

SRCC = $(addprefix $(DIR)/,$(SRC))
SRCO = $(SRC:%.scala=$(O_DIR)/%.class)
OBJS =	$(SRCO) $(T_SRCO)

CFLAGS =	-deprecation -encoding UTF-8 -unchecked -explaintypes \
			-Ywarn-dead-code -Ywarn-value-discard -Ywarn-numeric-widen \
			-Xcheckinit -Xfatal-warnings
RM = rm -rf
MKDIR = mkdir -p
I_DIR =

ifeq ($(shell uname), Linux)
SC = /home/mcanal/Downloads/scala-2.11.7/bin/scalac
S = /home/mcanal/Downloads/scala-2.11.7/bin/scala
else
SC = ~/scala/bin/scalac
S = ~/scala/bin/scala
endif

WHITE = \033[37;01m
RED = \033[31;01m
GREEN =  \033[32;01m
BLUE =  \033[34;01m
BASIC = \033[0m

.SILENT:
.PHONY: all debug clean fclean re

all: $(NAME)

debug: CFLAGS = 
debug: $(NAME)

$(NAME): $(SRCO)
#	@echo "$(BLUE)$< $(WHITE)->$(RED) $@ $(BASIC)"
	@echo "$(BLUE)$(SRCO) $(WHITE)->$(RED) $@ $(BASIC)"
	echo "$(S) -classpath $(O_DIR) Main \$$1 \$$2" > $@ && chmod 755 $@
	@echo "$(WHITE)compi:$(BASIC) $(SC)"
	@echo "$(WHITE)flags:$(BASIC) $(CFLAGS)"

$(SRCO): $(SRCC)
#	@echo "$(WHITE)$< ->$(BLUE) $@ $(BASIC)"
	@echo "$(WHITE)$(SRCC) ->$(BLUE) $(SRCO) $(BASIC)"
	$(SC) $(CFLAGS) -classpath $(SRCC) $< -d $(O_DIR)

$(OBJS): | $(O_DIR)

$(O_DIR):
	@$(MKDIR) $(O_DIR)

clean:
	@$(RM) $(O_DIR)

fclean: clean
	@$(RM) $(NAME)

re: fclean all
